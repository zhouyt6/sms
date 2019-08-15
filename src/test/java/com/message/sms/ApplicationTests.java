package com.message.sms;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
		List<String> dfdfd = Arrays.asList("dfdfd");
		ArrayList<String> erer = Lists.newArrayList("erer");
		log.info("eee :"+ff(1)+"4545"+ JSON.toJSONString(dfdfd)+"4545"+ JSON.toJSONString(erer));
		try {
			log.info("商机自动分配,提交参数:{} ;返回数据:{}",JSON.toJSONString(null) ,JSON.toJSONString(null));
		} catch (Exception e) {
			log.error("JSONObject.fromObject() isFailed", e);
		}

	}

	public boolean ff(Integer customerType) {
		Integer positionValue = customerType / (int) Math.pow(10, 1 - 1.0) % 10;
		return positionValue > 0;
	}

}
