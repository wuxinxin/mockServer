package com.example.demo.domain;

public class MockData {
    private Long id;

    private Long templatesId;

    private String params;

    private String fromData;

    private String raw;

    private String value;

    public MockData(Long id, Long templatesId, String params, String fromData, String raw, String value) {
        this.id = id;
        this.templatesId = templatesId;
        this.params = params;
        this.fromData = fromData;
        this.raw = raw;
        this.value = value;
    }

    public MockData() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplatesId() {
        return templatesId;
    }

    public void setTemplatesId(Long templatesId) {
        this.templatesId = templatesId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getFromData() {
        return fromData;
    }

    public void setFromData(String fromData) {
        this.fromData = fromData == null ? null : fromData.trim();
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw == null ? null : raw.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}