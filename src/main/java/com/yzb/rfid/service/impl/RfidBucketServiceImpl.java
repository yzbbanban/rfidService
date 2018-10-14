package com.yzb.rfid.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yzb.rfid.util.JsonParseUtils;
import com.yzb.rfid.dao.RfidBucketDAO;
import com.yzb.rfid.dao.RfidProductDAO;
import com.yzb.rfid.entity.Bucket;
import com.yzb.rfid.service.RfidBucketService;
import com.yzb.rfid.service.exception.RfidOrderException;

@Service
public class RfidBucketServiceImpl implements RfidBucketService {

    Logger logger = LoggerFactory.getLogger(RfidBucketServiceImpl.class.getName());

    @Resource
    RfidBucketDAO rfidBucketDAO;
    @Resource
    RfidProductDAO rfidProductDAO;

    @Resource
    private JsonParseUtils jsonParseUtils;

    public String addBucketListTask(String orderJs) {

        logger.info(orderJs);
        List<Bucket> rfidOrders = jsonParseUtils.getOrderObjList(orderJs, ArrayList.class, Bucket.class);
        logger.info("addBucketListTask:--> "+rfidOrders);
        int status = rfidOrders.get(0).getBucket_address();
        logger.info("---> " + status);
        switch (status) {// 新桶
            case -8:
                int exceptionCount8 = 0;
                String nullMsg8 = "";// 新桶
                for (int i = 0; i < rfidOrders.size(); i++) {

                    // logger.info(rfidOrders.size());
                    Bucket bucket = rfidOrders.get(i);
                    bucket.setCreate_time(new Date());
                    int row = 0;
                    String s = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());

                    if (s == null || "null".equals(s)) {
                        row = rfidBucketDAO.addOrderListTask(bucket);// 插入吨桶bucket数据
                    } else if ("1".equals(s)) {
                        // 已有
                    } else {
                        // 报废
                    }

                    logger.info("rfidBucketDAO-----> " + bucket);
                    int w = rfidBucketDAO.updateRecyOrderListTask(bucket);// 插入record表数据
                    logger.info(row + "," + w);
                    if (row != 1 || w != 1) {// 有一条不成功则失败
                        exceptionCount8++;
                    }

                }
                if (exceptionCount8 > 0) {
                    throw new RfidOrderException("提交失败" + exceptionCount8 + "笔数据，已存在吨桶" + nullMsg8);
                }

                break;
            case 0:// 空桶
                int exceptionCount = 0;
                String nullMsg = "";// 空桶
                for (int i = 0; i < rfidOrders.size(); i++) {

                    // logger.info(rfidOrders.size());
                    Bucket bucket = rfidOrders.get(i);
                    bucket.setCreate_time(new Date());
                    int row = 0;
                    int re = 0;
                    String s = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());
                    if (s == null) {
                        throw new RfidOrderException("提交失败:" + "可能不存在此桶");
                    }
                    if (bucket.getManufactor_id() == 0) {// 回收
                        String depot_code = rfidBucketDAO.getDepotTask(bucket);
                        // logger.info("depot_code---->" + depot_code);
                        bucket.setDepot_code(depot_code);
                        // bucket.setCustomer_id(-1);// bucket客户置空
                        // bucket.setProduct_code("1");// bucket产品表置空
                        String bucketAddress = rfidBucketDAO.getBucketAddressTask(bucket.getBucket_code());
                        String pr = rfidBucketDAO.findBucketCodeByName(bucket.getBucket_code());
                        int bd = Integer.parseInt(bucketAddress);

                        if (bd >= 3 && (bucket.getProduct_code() == null || "".equals(bucket.getProduct_code()))) {// 在客户区可回收
                            if ("1".equals(s)) {
                                // 已有
                                row = rfidBucketDAO.updateOrderListMa(bucket);// 更新吨桶bucket数据
                                if (bucket.getProduct_code() == null) {
                                    re = rfidBucketDAO.updateRecordCount(bucket);
                                }
                            } else {
                                // 报废或没有
                            }
                        } else if (bd == -8) {// 新桶进入空桶区 包装桶入库
                            row = rfidBucketDAO.updateOrderListTask(bucket);// 更新吨桶bucket数据
                        } else if (pr == null || "".equals(pr)) {// 重置的空桶
                            row = rfidBucketDAO.updateOrderListTask(bucket);// 更新吨桶bucket数据
                        } else {
                            nullMsg = "或有桶不该回收";
                        }

                    }

                    logger.info("rfidBucketDAO-----> " + bucket);
                    int w = rfidBucketDAO.updateRecyOrderListTask(bucket);// 插入record表数据
                    logger.info(row + "," + w);
                    if (row != 1 || w != 1) {// 有一条不成功则失败
                        exceptionCount++;
                    }

                }
                if (exceptionCount > 0) {
                    throw new RfidOrderException("提交失败" + exceptionCount + "笔数据，此桶已在空桶区，并已绑定" + nullMsg);
                }

                break;
            case 1:// 产品
                int exceptionCount2 = 0;
                boolean isProductBind = false;
                // 先遍历循环是否桶已绑定产品，绑定则全部不更新，返回数据为未成功的数据
                // 用于记录错误数据的 list
                List<Bucket> buckets = new ArrayList<Bucket>();
                for (int i = 0; i < rfidOrders.size(); i++) {

                    // logger.info(rfidOrders.size());
                    Bucket bucket = rfidOrders.get(i);
                    // 查询桶是否绑定正确
                    String productCode = bucket.getProduct_code();
                    String productCodeResult = rfidBucketDAO.findBucketCodeByName(bucket.getBucket_code());
                    if (productCodeResult != null // 为 null 说明没绑定，可进入绑定
                            && !productCodeResult.equals(productCode)) {// 若有绑定的产品，且不与绑定的相同，抛出异常
                        isProductBind = true;
                        buckets.add(bucket);
                    }

                }
                if (isProductBind) {
                    throw new RfidOrderException(jsonParseUtils.getJsonParse(buckets));
                }
                String msg = "";
                for (int i = 0; i < rfidOrders.size(); i++) {

                    // logger.info(rfidOrders.size());
                    Bucket bucket = rfidOrders.get(i);
                    // 查询桶状态
                    String s = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());
                    bucket.setCreate_time(new Date());
                    logger.info("------>" + bucket);
                    int r = 0;
                    int row = 0;
                    int w = 0;
                    String bucketAddress = rfidBucketDAO.getBucketAddressTask(bucket.getBucket_code());
                    int bd = 0;
                    if (bucketAddress == null) {
                        throw new RfidOrderException("提交失败:" + "可能不存在此桶");

                    } else {
                        bd = Integer.parseInt(bucketAddress);
                    }
                    if (bd >= 1) {// 已出库则不能再此出库
                        logger.info("重复产品区上传");
                        msg = "或重复产品区上传";
                    } else {
                        if ("1".equals(s)) {
                            // 已有
                            r = rfidBucketDAO.updateOrderListTask(bucket);// 更新吨桶数据位置状态
                            row = rfidBucketDAO.updateProOrderListTask(bucket);
                            w = rfidBucketDAO.updateRecyOrderListTask(bucket);// 插入record表数据
                        } else {
                            // 报废或没有

                        }
                    }

                    if (row != 1 || r != 1 || w != 1) {// 有一条不成功则失败
                        exceptionCount2++;
                    }

                }
                if (exceptionCount2 > 0) {
                    throw new RfidOrderException("提交失败" + exceptionCount2 + "笔数据，已存在吨桶" + msg);
                }
                break;
            case 2:// 客户 （不用）
                int exceptionCount3 = 0;

                for (int i = 0; i < rfidOrders.size(); i++) {

                    logger.info("===>"+rfidOrders.size());
                    Bucket bucket = rfidOrders.get(i);
                    String s = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());
                    bucket.setCreate_time(new Date());
                    int r = 0;
                    int row = 0;
                    int w = 0;

                    if ("1".equals(s)) {
                        // 已有
                        r = rfidBucketDAO.updateOrderListTask(bucket);// 更新吨桶数据位置状态
                        logger.info("客户-----> " + bucket);

                        row = rfidBucketDAO.updateCustOrderListTask(bucket);// 插入客户吨桶关联
                        w = rfidBucketDAO.updateRecyOrderListTask(bucket);// 插入record表数据
                    } else {
                        // 报废或没有
                    }

                    if (row != 1 || r != 1 || w != 1) {// 有一条不成功则失败
                        exceptionCount3++;
                    }

                }
                int id = rfidOrders.get(0).getCustomer_id();
                // 更新产品的吨桶数量
                int row = rfidBucketDAO.setOrderCustomerCountTask(id);
                if (row != 1) {
                    // throw new RfidOrderException("提交有误，请重新上传");
                }
                if (exceptionCount3 > 0) {
                    throw new RfidOrderException("提交失败" + exceptionCount3 + "笔数据，已存在吨桶");
                }
                break;
            case 3:// 在途
                int exceptionCount4 = 0;
                boolean isCustmerBind = false;
                // 先遍历循环是否桶已绑定客户，绑定则全部不更新，返回数据为未成功的数据
                // 用于记录错误数据的 list
                List<Bucket> bucketList = new ArrayList<Bucket>();
                for (int i = 0; i < rfidOrders.size(); i++) {

                    logger.info("在途====>"+rfidOrders.size());
                    Bucket bucket = rfidOrders.get(i);
                    int custormer = bucket.getCustomer_id();
                    int custormerResult = rfidBucketDAO.findCustormerByName(bucket.getBucket_code());
                    logger.info("cust: " + custormer + " : " + custormerResult);
                    if (custormerResult != 0 && custormer != custormerResult) {
                        isCustmerBind = true;
                        bucketList.add(bucket);
                    }

                }

                if (isCustmerBind) {
                    throw new RfidOrderException(jsonParseUtils.getJsonParse(bucketList));
                }
                String cusMsg = "";
                for (int i = 0; i < rfidOrders.size(); i++) {
                    logger.info("在途===>"+rfidOrders.size());
                    Bucket bucket = rfidOrders.get(i);
                    bucket.setCreate_time(new Date());
                    String depot_code = rfidBucketDAO.getDepotTask(bucket);
                    bucket.setDepot_code(depot_code);
                    String s = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());
                    String productCode = rfidBucketDAO.getProductTask(bucket.getBucket_code());
                    bucket.setProduct_code(productCode);
                    logger.info("在途:产品-----> " + bucket);
                    int r = 0;
                    int o = 0;
                    int w = 0;

                    String bucketStatus = rfidBucketDAO.getBucketStatusTask(bucket.getBucket_code());
                    String bucketAddress = rfidBucketDAO.getBucketAddressTask(bucket.getBucket_code());
                    int bd = 0;
                    if (bucketAddress == null) {
                        throw new RfidOrderException("提交失败:" + "可能不存在此桶");
                    } else {
                        bd = Integer.parseInt(bucketAddress);
                    }

                    if (bd >= 2) {// 已出库则不能再此出库
                        logger.info("在途:重复客户区上传");
                        cusMsg = "或重复客户区上传";
                    } else if (bd == 0) {// 空桶区
                        logger.info("在途:或吨桶错误，还在空桶区");
                        cusMsg = "或吨桶错误，还在空桶区";
                    } else {
                        if ("1".equals(s)) {
                            // 已有
                            o = rfidBucketDAO.updateCustOrderListTask(bucket);// 插入客户吨桶关联
                            r = rfidBucketDAO.updateOrderListTask(bucket);// 更新吨桶数据位置状态
                            w = rfidBucketDAO.updateRecyOrderListTask(bucket);

                        } else {
                            // 报废或没有

                        }
                    }

                    if (o != 1 || w != 1 || r != 1) {// 有一条不成功则失败
                        exceptionCount4++;
                    }

                }
                if (exceptionCount4 > 0) {
                    throw new RfidOrderException("提交失败" + exceptionCount4 + "笔数据，已存在吨桶" + cusMsg);
                }
                break;
            case 4://客户到产品区
                int exceptionCount44 = 0;
                String nullMsg44 = "";
                for (int i = 0; i < rfidOrders.size(); i++) {

                    // logger.info(rfidOrders.size());
                    Bucket bucket = rfidOrders.get(i);
                    bucket.setCreate_time(new Date());
                    String bucketAddress44 = rfidBucketDAO.getBucketAddressTask(bucket.getBucket_code());
                    int bdStatus = -10;
                    if (bucketAddress44 == null) {
                        throw new RfidOrderException("提交失败:" + "可能不存在此桶");
                    } else {
                        bdStatus = Integer.parseInt(bucketAddress44);
                    }
                    int r = 0;
                    int w = 0;
                    //状态必须是在途状态
                    if (bdStatus == 3) {
                        //将在途状态的桶职位产品状态
                        bucket.setBucket_address(1);
                        r = rfidBucketDAO.updateOrderListTask(bucket);// 更新吨桶数据位置状态
                        w = rfidBucketDAO.updateRecyOrderListTask(bucket);

                    } else {
                        throw new RfidOrderException("提交失败:" + "此桶不在客户区");
                    }

                    if (w != 1 || r != 1) {// 有一条不成功则失败
                        exceptionCount44++;
                    }

                }
                if (exceptionCount44 > 0) {
                    throw new RfidOrderException("提交失败" + exceptionCount44 + "笔数据，已存在吨桶" + nullMsg44);
                }

                break;
            case 5://产品到空桶
                int exceptionCount5 = 0;
                String nullMsg5 = "";
                for (int i = 0; i < rfidOrders.size(); i++) {

                    // logger.info(rfidOrders.size());
                    Bucket bucket = rfidOrders.get(i);
                    bucket.setCreate_time(new Date());
                    String bucketAddress5 = rfidBucketDAO.getBucketAddressTask(bucket.getBucket_code());
                    int bdStatus = -10;
                    if (bucketAddress5 == null) {
                        throw new RfidOrderException("提交失败:" + "可能不存在此桶");
                    } else {
                        bdStatus = Integer.parseInt(bucketAddress5);
                    }
                    int r = 0;
                    int w = 0;
                    //状态必须是产品状态
                    if (bdStatus == 1) {
                        //修改为空桶状态
                        bucket.setBucket_address(0);
                        r = rfidBucketDAO.updateOrderListTask(bucket);// 更新吨桶数据位置状态
                        w = rfidBucketDAO.updateRecyOrderListTask(bucket);

                    } else {
                        throw new RfidOrderException("提交失败:" + "此桶不在产品区");
                    }

                    if (w != 1 || r != 1) {// 有一条不成功则失败
                        exceptionCount5++;
                    }

                }
                if (exceptionCount5 > 0) {
                    throw new RfidOrderException("提交失败" + exceptionCount5 + "笔数据，已存在吨桶" + nullMsg5);
                }

                break;
        }

        return "插入成功";
    }

}
