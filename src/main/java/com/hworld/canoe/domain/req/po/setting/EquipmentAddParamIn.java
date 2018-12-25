package com.hworld.canoe.domain.req.po.setting;

import java.io.Serializable;

import lombok.Data;

/**
 * 新增惯用器材类型入参
 * @author xichonghang
 */
@Data
public class EquipmentAddParamIn implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
}

