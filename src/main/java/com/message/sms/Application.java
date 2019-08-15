package com.message.sms;

import cn.hutool.core.util.URLUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class Application {

	@Autowired
	SendSms sendSms;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("main --------------------- end");
		System.out.println("main --------------------- end----------");
//		sendSms.haha();
		System.exit(0);
	}

	@Bean
	public ApplicationRunner runner() {
		return args -> {
//			sendSms.haha();
            ExcelReader reader = ExcelUtil.getReader("E:/data/新拓词.xlsx");
            List<Map<String,Object>> readAll = reader.readAll();
            readAll.forEach((Map<String, Object> map) -> {
                String string = map.get("URL").toString();
                String url = URLUtil.decode(string);
                log.info(url);
                System.out.println(url);
            });
		};
	}

}
