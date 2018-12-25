package com.hworld.canoe.framework.msg.req;

import java.io.Serializable;

public class OptionRequest implements Serializable {
    String id;
    String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
