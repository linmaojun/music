package com.music.domain.close;

import lombok.Data;

/**
 * @author linmaojun
 * 类名称: header
 * 类描述:
 * 创建时间: 2019年07月18日  11:41
 */
@Data
public class Header {
    /**
     * 业务类型
     */
    private String businessType;

    /**
     *
     */
    private JsonMessageType jsonMessageType;

    /**
     *
     */
    private String message;

    /**
     * 文件发送方来源
     */
    private String sendSource;

    /**
     * 发送日期
     */
    private String sendTime;

    /**
     *
     */
    private String sendType;

    /**
     * 文件发送方
     */
    private String sender;

    /**
     *
     */
    private String status;

}
