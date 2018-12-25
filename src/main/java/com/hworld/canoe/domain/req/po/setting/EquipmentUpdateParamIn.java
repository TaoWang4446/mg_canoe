package com.hworld.canoe.domain.req.po.setting;

import java.io.Serializable;

import lombok.Data;

/**
 * 修改惯用器材类型入参
 * @author xichonghang
 */
@Data
public class EquipmentUpdateParamIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
}

