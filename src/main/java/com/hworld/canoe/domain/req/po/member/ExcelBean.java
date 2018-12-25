package com.hworld.canoe.domain.req.po.member;

import java.io.Serializable;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import lombok.Data;

/**
 * 对于excel文件，解析它的数据
 * 
 * @author xichonghang
 */
@Data
public class ExcelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String headTextName;// 列头（标题）名
	private String propertyName;// 对应字段名
	private Integer cols;// 合并单元格数
	private XSSFCellStyle cellStyle;
}
