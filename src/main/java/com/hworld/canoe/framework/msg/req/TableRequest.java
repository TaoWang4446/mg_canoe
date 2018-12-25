package com.hworld.canoe.framework.msg.req;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TableRequest implements Serializable {

    private static final long serialVersionUID = 7328071045193618467L;

    private Integer offset;
    private Integer limit;
    private Map<String, Object> params;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getOffset() {
        return offset + 1;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            return new HashMap<String, Object>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}