package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.MockDataMapper;
import com.example.demo.dao.MockTemplatesMapper;
import com.example.demo.domain.MockData;
import com.example.demo.domain.MockTemplates;
import com.example.demo.utils.StrCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xinxin.wu on 2020/1/7.
 */
@Service
public class MockService {
    @Autowired
    private MockDataMapper mockDataMapper;

    @Autowired
    private MockTemplatesMapper mockTemplatesMapper;

    @Autowired
    private StrCheck strCheck;

    //跟据请求获取mock数据
    public JSONObject getMockData(String url, String method, Map<String, String[]> requestMap, String query_str, String body) {
        //跟据method、url得到模板
        MockTemplates mockTemplates = mockTemplatesMapper.selectByMethodAndUrl(method, url);
        //跟据TemplatesID得到mockdata列表
        List<MockData> mockDataList = mockDataMapper.selectByTemplatesID(mockTemplates.getId());
        //从mockdata列表中，找到匹配的mockdata
        MockData mockData = getMockData(requestMap, query_str, body, mockDataList);
        //得到mockTemplates设置的返回值
        String resData = mockTemplates.getData();
        //如果mockdata中无匹配的，则直接放回模块默认data数据
        if (mockData == null) {
            return JSON.parseObject(resData, JSONObject.class);
        } else {
            return strCheck.getMockResData(resData, mockData.getValue());
        }
    }

    //从mockDataList列表中，找到唯一匹配行
    private MockData getMockData(Map<String, String[]> requestMap, String query_str, String body, List<MockData> mockDataList) {
        if (query_str != null && query_str.length() != 0) {   //query_str不为空
            for (int i = 0; i < mockDataList.size(); i++) {
                MockData nowMockData = mockDataList.get(i);
                //mockdata表中设置的params
                String nowMockDataParams = nowMockData.getParams();
                //匹配params
                if (strCheck.matchParams(nowMockDataParams, query_str)) {
                    return nowMockData;
                }
            }
        } else if (body != null && body.length() != 0) {
            for (int i = 0; i < mockDataList.size(); i++) {
                MockData nowMockData = mockDataList.get(i);
                //mockdata表中设置的raw
                String mockDataRaw = nowMockData.getRaw();
                //匹配raw
                if (strCheck.matchRaw(mockDataRaw, body)) {
                    return nowMockData;
                }
            }
        } else if (requestMap != null && !requestMap.isEmpty()) {
            for (int i = 0; i < mockDataList.size(); i++) {
                MockData nowMockData = mockDataList.get(i);
                //mockdata表中设置的from_data
                String nowMockDataFromData = nowMockData.getFromData();
                //匹配from_data
                if (strCheck.matchFromData(nowMockDataFromData, requestMap)) {
                    return nowMockData;
                }
            }
        }
        //找不到匹配项，则返回null
        return null;
    }
}
