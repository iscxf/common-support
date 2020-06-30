package com.iscxf.common.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author chenxf
 * Created on 2020/04/29
 */
@Data
public class LogExcelData {

    /**
     * 单据类型，这里返回的是apiName要进行自定义转换
     */
    @ExcelProperty(value = "单据类型", converter = CustomStringConverter.class)
    private String apiName;
    /**
     * 单据编号
     */
    @ExcelProperty(value = "单据编号")
    private String dataId;
    /**
     * 操作类型(0新增/1修改/2作废/3删除)
     * */
    @ExcelProperty(value = "操作类型", converter = CustomStringConverter.class)
    private String operation;
    /**
     * 错误信息
     */
    @ExcelProperty(value = "错误信息")
    private String errorMsg;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 写到excel 用百分比表示
     */
    @NumberFormat("#.##%")
    @ExcelProperty(value = "数字标题")
    private Double doubleData;
}
