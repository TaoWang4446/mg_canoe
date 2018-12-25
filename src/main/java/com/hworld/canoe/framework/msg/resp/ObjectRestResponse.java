package com.hworld.canoe.framework.msg.resp;

public class ObjectRestResponse<T> extends BaseResponse {

    T data;
    boolean rel;

    public ObjectRestResponse() {
    }

    public ObjectRestResponse(T data) {
        super(200, "");
        this.data = data;
    }

    public ObjectRestResponse(int status, String message) {
        super(status, message);
    }

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }


    public ObjectRestResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }


    public ObjectRestResponse data(T data) {
        this.setData(data);
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
