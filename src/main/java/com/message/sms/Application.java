package com.message.sms;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.message.sms.pojo.UrlEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        log.info("main --------------------- start");
        SpringApplication.run(Application.class, args);
        log.info("main --------------------- end");
//		System.out.println("main --------------------- end----------");
        System.exit(0);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            AtomicInteger successNum = new AtomicInteger(0);
            AtomicInteger errNum = new AtomicInteger(0);
            String bookFilePath = null;
            String name = "";
            try {
                File file = new File("D:/data");
                String[] fileNameList = file.list();
                for (String fileName : fileNameList) {
                    if (fileName.endsWith(".xlsx")) {
                        bookFilePath = "D:/data/" + fileName;
                        name = fileName.substring(0, fileName.lastIndexOf("."));
                        break;
                    }
                }
            } catch (Exception e) {
                log.error("file is not find==================", e);
//				System.out.println("file is not find==================");
                e.printStackTrace();
            }
            ExcelReader reader = ExcelUtil.getReader(bookFilePath);
            List<Map<String, Object>> readAll = reader.readAll();
            ArrayList<UrlEntity> resultUrlList = new ArrayList<>();
            int sizeAll = readAll.size();
            Boolean success = true;
            for (int i = 0; i < sizeAll; i++) {
                Map<String, Object> map = readAll.get(i);
                String encodeUrl = "";
                String url = "";
                try {
                    Object urlObject = map.get("链接");
                    if (urlObject == null) {
                        urlObject = map.get("URL");
                    }
                    if (urlObject == null) {
                        success = false;
                        break;
                    }
                    encodeUrl = urlObject.toString();
                } catch (Exception e) {
                    success = false;
                    break;
                }
                try {
                    url = URLUtil.decode(encodeUrl);
                    if (url.contains("%")) {
                        errNum.incrementAndGet();
                        int id = i + 2;
                        log.error(id + "\t" + encodeUrl + "\t" + url);
                        resultUrlList.add(new UrlEntity(id,encodeUrl,url));
                        continue;
                    }
                    String keywordPromotion = url.substring(url.lastIndexOf("=") + 1, url.length());

                    String[] keywords = keywordPromotion.split("_");
                    if (keywords.length < 6) {
                        errNum.incrementAndGet();
                        int id = i + 2;
                        log.error(id + "\t" + encodeUrl + "\t" + url);
                        resultUrlList.add(new UrlEntity(id,encodeUrl,url));
                        continue;
                    }
                    successNum.incrementAndGet();
                } catch (Exception e) {
                    errNum.incrementAndGet();
                    int id = i + 2;
                    log.error(id + "\t" + encodeUrl + "\t" + url);
                    resultUrlList.add(new UrlEntity(id,encodeUrl,url));
                }
            }
            if (success) {
                log.info("总数量Total:" + sizeAll + ",  成功successNum:" + successNum + ",  失败errNum:" + errNum);
            } else {
                log.error("没有发现--URL或者链接标题头------------------------");
            }
//			System.out.println("总数量Total:" + sizeAll + ", 成功successNum:" + successNum + ", 失败errNum:" + errNum);
            if (CollectionUtil.isNotEmpty(resultUrlList)) {
                log.info("生成Excel到桌面-------------------------开始");
                writeToExcel(resultUrlList,name);
            }
        };

    }

    private void writeToExcel(ArrayList<UrlEntity> resultUrlList,String name) {
        Map<String,String> map = System.getenv();
//            System.out.println(map.get("USERNAME"));//获取用户名    C:\Users\EDZ\Desktop\elasticsearch.yml
        // 通过工具类创建writer
        String username = map.get("USERNAME");
        String path = "C:/Users/"+username+"/Desktop/"+name+"--不规范URL-"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".xlsx";
        ExcelWriter writer = ExcelUtil.getWriter(path);

        writer.addHeaderAlias("id", "原excel中行号");
        writer.addHeaderAlias("url_old", "原链接");
        writer.addHeaderAlias("url", "解密后链接");
        writer.merge(2, "不规范URL");
        writer.write(resultUrlList, true);
        writer.setColumnWidth(0,10);
        writer.setColumnWidth(1,80);
        writer.setColumnWidth(2,80);
        writer.close();
    }

}
