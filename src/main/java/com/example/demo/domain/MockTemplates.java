package com.example.demo.domain;

public class MockTemplates {
    private Long id;

    private String method;

    private String url;

    private String data;

    public MockTemplates(Long id, String method, String url, String data) {
        this.id = id;
        this.method = method;
        this.url = url;
        this.data = data;
    }

    public MockTemplates() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
}