package com.hworld.canoe.framework.msg.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by dlgu on 2015/12/17.
 */
public abstract class DomainBase {

    @JsonIgnore
    protected final String defaultTimeZone = "GMT+8";
}
