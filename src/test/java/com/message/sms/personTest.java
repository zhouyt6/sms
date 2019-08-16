package com.message.sms;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.util.Map;

/**
 * @Author: ZYT
 * @Date: 2019/08/09
 * @Description:
 */

public class personTest {

    public static void main(String[] args) throws Exception{

        File file = new File("E:/data");
        String[] list = file.list();
        String bookFilePath = null;
        for (String fileName : list) {
            if (fileName.endsWith(".xlsx")) {
                bookFilePath = "E:/data/"+fileName;
                System.out.println(bookFilePath);
                break;
            }
        }
        ExcelReader reader = ExcelUtil.getReader(bookFilePath);
////        读取为Bean列表，Bean中的字段名为标题，字段值为标题对应的单元格值。
//
//        List<Person> excels = reader.readAll(Person.class);
//        excels.forEach(x -> System.out.print(x));


        Map<String,String> map = System.getenv();
        System.out.println(map.get("USERNAME"));//获取用户名
        System.out.println(map.get("COMPUTERNAME"));//获取计算机名
        System.out.println(map.get("USERDOMAIN"));//获取计算机域名


    }
}
