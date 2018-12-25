package com.hworld.canoe.domain.req.vo.member;

import java.io.Serializable;

import lombok.Data;

/**
 * 器材类型出参数
 * @author xichonghang
 */
@Data
public class EquipmentTypeOutParam implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
}
