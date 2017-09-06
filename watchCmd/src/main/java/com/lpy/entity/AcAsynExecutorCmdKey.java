package com.lpy.entity;

public class AcAsynExecutorCmdKey {
    private String tntInstId;

    private String id;

    public String getTntInstId() {
        return tntInstId;
    }

    public void setTntInstId(String tntInstId) {
        this.tntInstId = tntInstId == null ? null : tntInstId.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
}