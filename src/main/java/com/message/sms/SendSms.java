package com.message.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bj58.qifu.sms.agent.SmsService;
import com.message.sms.pojo.TwoField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;

/**
 * @Author: ZYT
 * @Date: 2019/08/06
 * @Description:
 */

@Slf4j
@Component
public class SendSms {

    @Reference
    SmsService smsService;

    public void haha() {
        int successNum = 0;
        int errNum = 0;
        TwoField<String, ArrayList<String>> twoField = getArrayList();
        String msg = twoField.getT();
        ArrayList<String> arrayList = twoField.getU();
        for (int i = 0; i < arrayList.size(); i++) {
            String mobile = arrayList.get(i);
            try {
                smsService.send(mobile, msg);
                successNum++;
            } catch (Exception e) {
                errNum++;
                log.error("send err mobile :{}", mobile, e);
                System.out.println("send err mobile :" + mobile);
            }
        }
        log.info("发送总数量Total:" + arrayList.size() + ", 成功successNum:" + successNum + ", 失败errNum:" + errNum);
        System.out.println("发送总数量Total:" + arrayList.size() + ", 成功successNum:" + successNum + ", 失败errNum:" + errNum);
    }

    public TwoField<String, ArrayList<String>> getArrayList() {
        TwoField<String, ArrayList<String>> twoField = new TwoField<>();
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String jarWholePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
            jarWholePath = java.net.URLDecoder.decode(jarWholePath, "UTF-8");
            String jarPath = new File(jarWholePath).getParentFile().getAbsolutePath();

            // 文件放置在target目录下
//            File file = new File(jarPath + "\\sendPhone.txt");
            File file = new File("E:/data/sendPhone.txt");
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bf = new BufferedReader(inputReader);
            String msg = bf.readLine();
            twoField.setT(msg);
            log.info("msg:" + msg);
            System.out.println("msg :" + msg);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            inputReader.close();
            twoField.setU(arrayList);
        } catch (IOException e) {
            log.error("read err:", e);
            System.out.println("read err:" + e);
        }
        return twoField;
    }

}
