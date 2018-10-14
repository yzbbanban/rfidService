package com.yzb.rfid.dao;


import org.springframework.stereotype.Repository;

import com.yzb.rfid.entity.Bucket;

public interface RfidBucketDAO {
	/**
	 * 添加吨桶数据
	 * 
	 * @param bucket
	 * @return
	 */
	int addOrderListTask(Bucket bucket);

	/**
	 * 添加吨桶数据
	 * 
	 * @param bucket
	 * @return
	 */
	int updateOrderListMa(Bucket bucket);

	/**
	 * 获取吨桶数据
	 * 
	 * @param bucket
	 * @return
	 */
	String getDepotTask(Bucket bucket);

	/**
	 * 获取吨桶产品
	 * 
	 * @param bucket
	 * @return
	 */
	String getProductTask(String bucket);

	/**
	 * 插入吨桶客户数量
	 * 
	 * @param id
	 * @return
	 */
	int setOrderCustomerCountTask(int id);

	/**
	 * 添加吨桶数据
	 * 
	 * @param bucket
	 * @return
	 */
	int updateOrderListTask(Bucket bucket);

	/**
	 * 更新产品吨桶数据
	 * 
	 * @param bucket
	 * @return
	 */
	int updateProOrderListTask(Bucket bucket);

	/**
	 * 更新客户吨桶数据
	 * 
	 * @param bucket
	 * @return
	 */
	int updateCustOrderListTask(Bucket bucket);

	/**
	 * 更新回收吨桶数据
	 * 
	 * @param bucket
	 * @return
	 */
	int updateRecyOrderListTask(Bucket bucket);

	/**
	 * 获取吨桶状态
	 * 
	 * @param bucket
	 * @return
	 */
	String getBucketStatusTask(String bucket);

	/**
	 * 根据桶编号查询产品是否一致
	 * 
	 * @param bucket_code
	 * @return
	 */
	String findBucketCodeByName(String bucket_code);

	/**
	 * 根据桶编号查询客户是否一致
	 * @param bucket_code
	 * @return
	 */
	int findCustormerByName(String bucket_code);

	String getBucketAddressTask(String bucket_code);

	/**
	 * 插入周转次数
	 * @param bucket
	 * @return
	 */
	int updateRecordCount(Bucket bucket);
	

}
