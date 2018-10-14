package com.yzb.rfid.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzb.rfid.entity.Bucket;

@Repository("jsonParseUtils")
public class JsonParseUtils {
    public final ObjectMapper mapper = new ObjectMapper();

    private JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {

        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 解析为集合对象
     *
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getOrderObjList(String orderJson, Class<?> collectionClass, Class<?>... elementClasses) {
        try {
            return (List<T>) mapper.readValue(orderJson, getCollectionType(ArrayList.class, Bucket.class));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 解析为单个对象
     *
     * @param json
     * @param cls
     * @return
     */
    public <T> T getObj(String json, Class<T> cls) {
        try {
            return (T) mapper.readValue(json, cls);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> String getJsonParse(T cls) {
        String jsonParse;
        try {
            jsonParse = mapper.writeValueAsString(cls);
            return jsonParse;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
