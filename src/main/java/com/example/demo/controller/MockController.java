package com.example.demo.controller;

//import com.fasterxml.jackson.databind.util.JSONPObject;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.MockService;
import com.example.demo.utils.ReqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by xinxin.wu on 2019/12/31.
 */

@RestController
@RequestMapping("/")
public class MockController {
    @Autowired
    private MockService mockService;

    @Autowired
    private ReqUtil reqUtil;

    @RequestMapping("**")
    public JSONObject mockall(HttpServletRequest request) {
        String body = null;
        Map<String, String[]> requestMap = request.getParameterMap();
        String method = request.getMethod();

        String url = request.getRequestURI();
        String query_str = request.getQueryString();
        try {
            body = reqUtil.getBody(request);
        } catch (IOException io) {
            System.out.print("error");
        }

        JSONObject result = mockService.getMockData(url, method, requestMap, query_str, body);

        return result;
    }
}
