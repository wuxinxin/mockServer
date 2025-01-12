package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * Created by xinxin.wu on 2020/1/15.
 */
@Component
public class StrCheck {
    public boolean matchRaw(String mockDataRaw, String requestRaw) {
        //mockDataRaw 如 $.appCode=wxx1;$.tableName=ddd
        String[] mockDataRawArray = mockDataRaw.split(";");
        for (int i = 0; i < mockDataRawArray.length; i++) {
            //mockDataRawOne 如 $.appCode=wxx1
            String mockDataRawOne = mockDataRawArray[i];
            String[] keyAndValueArray = mockDataRawOne.split("=");
            String key = keyAndValueArray[0];
            String value = keyAndValueArray[1];

            Object requestValue = JSONPath.read(requestRaw, key);
            //如果value==requestValue,则单次匹配成功
            if (value.equals(requestValue)) {
                continue;
            } else {
                //只要有一次不匹配，则返回false
                return false;
            }
        }
        //全部匹配成功，则返回true
        return true;
    }

    public boolean matchFromData(String mockDataFromData, Map<String, String[]> requestFromData) {
        //from-data 如 name=wxx&email=wxx@msxf.com
        String[] mkFromDataArray = mockDataFromData.split("&");
        for (int i = 0; i < mkFromDataArray.length; i++) {
            //mockDataRawOne 如 $.appCode=wxx1
            String mkFromDataOne = mkFromDataArray[i];
            String[] keyAndValueArray = mkFromDataOne.split("=");
            String key = keyAndValueArray[0];
            String value = keyAndValueArray[1];

            //如果map中存在key，并且此key的value[0]等于value
            if (requestFromData.containsKey(key) && value.equals(requestFromData.get(key)[0])) {
                continue;
            } else {
                //只要有一次不匹配，则返回false
                return false;
            }
        }
        return true;
    }

    public boolean matchParams(String mockDataParams, String requestParams) {
        //params 如 name=wxx&email=wxx@msxf.com
        String[] mockDataParamsArray = mockDataParams.split("&");
        String[] requestParamsArray = requestParams.split("&");
        ArrayList mockList = new ArrayList<String>();
        ;
        Collections.addAll(mockList, mockDataParamsArray);
        ArrayList reqList = new ArrayList<String>();
        ;
        Collections.addAll(reqList, requestParamsArray);
        boolean ss = reqList.containsAll(mockList);
        //如果mockList是reqList的子集，则匹配成功
        if (reqList.containsAll(mockList)) {
            return true;
        } else {
            return false;
        }
    }

    public JSONObject getMockResData(String resData, String mockDataValue) {
        String[] mockDataValueArray = mockDataValue.split(";");
        JSONObject jsonObject = JSON.parseObject(resData, JSONObject.class);
        for (int i = 0; i < mockDataValueArray.length; i++) {
            String mockDataValueOne = mockDataValueArray[i];
            String[] keyAndValueArray = mockDataValueOne.split("=");
            String key = keyAndValueArray[0];
            String value = keyAndValueArray[1];
            //如果存在此jsonpath，则更新他的值
            if (JSONPath.contains(jsonObject, key)) {
                JSONPath.set(jsonObject, key, value);
            }
        }
        return jsonObject;
    }
}
