package com.message.sms;

import cn.hutool.core.util.URLUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;
import java.util.Map;

/**
 * @Author: ZYT
 * @Date: 2019/08/09
 * @Description:
 */

public class TTTT {

    public static void main(String[] args) {

//       String url = "http://bj.daojia.com/duigong?cate=residentialcleaning&source=pc&hmsr=sem_baidu_pc&cmpid=sem_baidu_cpc_PC-%e4%bf%9d%e6%b4%81-%e6%96%b0%e5%a2%9e%e8%af%8d_%e4%bf%9d%e6%b4%81-%e9%95%bf%e6%9c%9f-%e6%a0%b8%e5%bf%83_%e4%bc%81%e4%b8%9a%e4%bf%9d%e6%b4%81%e9%95%bf%e6%9c%9f";
//
//        String decode = URLUtil.decode(url);
//
//        System.out.println(decode);

//        File file = new File("E:/data/sendPhone.txt");
//        System.out.println(file.getAbsolutePath());
        ExcelReader reader = ExcelUtil.getReader("E:/data/新拓词.xlsx");
        List<Map<String,Object>> readAll = reader.readAll();
        readAll.forEach((Map<String, Object> map) -> {
            String string = map.get("URL").toString();
            String url = URLUtil.decode(string);
            System.out.println(url);
        });
//        for (Map<String, Object> map : readAll) {
//            System.out.println(map.get("URL").toString());
//        }

    }
}
