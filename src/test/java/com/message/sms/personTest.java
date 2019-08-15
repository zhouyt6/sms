package com.message.sms;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.message.sms.pojo.ExcelDEMO;

import java.util.List;

/**
 * @Author: ZYT
 * @Date: 2019/08/09
 * @Description:
 */

public class personTest {

    public static void main(String[] args) throws Exception{

        ExcelReader reader = ExcelUtil.getReader("E:/aaa.xlsx");
//        读取为Bean列表，Bean中的字段名为标题，字段值为标题对应的单元格值。

        List<ExcelDEMO> excels = reader.readAll(ExcelDEMO.class);
        excels.forEach(x -> System.out.print(x));

    }
}
