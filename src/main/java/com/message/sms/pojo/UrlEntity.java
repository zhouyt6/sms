package com.message.sms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZYT
 * @Date: 2019/08/15
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity {

    private Integer id;

    private String url_old;

    private String url;

}
