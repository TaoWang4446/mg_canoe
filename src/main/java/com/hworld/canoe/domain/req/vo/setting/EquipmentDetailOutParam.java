package com.hworld.canoe.domain.req.vo.setting;

import java.io.Serializable;

import lombok.Data;

/**
 * 器材类型出参
 * @author xichonghang
 */
@Data
public class EquipmentDetailOutParam implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
}

