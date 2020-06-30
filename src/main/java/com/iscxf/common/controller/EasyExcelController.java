package com.iscxf.common.controller;

import com.alibaba.excel.EasyExcel;
import com.iscxf.common.easyexcel.LogExcelData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxf
 * Created on 2020/4/29
 */
@Controller
@Slf4j
public class EasyExcelController {

    /**
     * 日志excel下载（失败了会返回一个有部分数据的Excel）
     * finish的时候会自动关闭OutputStream
     */
    @PostMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response, String arg) throws IOException {
//        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试下载", "UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<LogExcelData> logExcelData = new ArrayList<>();
        EasyExcel.write(response.getOutputStream(), LogExcelData.class).sheet("sheet1").doWrite(logExcelData);
    }
}
