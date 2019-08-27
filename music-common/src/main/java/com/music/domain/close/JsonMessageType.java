package com.music.domain.close;

import lombok.Data;

/**
 * @author linmaojun
 * 类名称: Info
 * 类描述:
 * 创建时间: 2019年07月18日  11:52
 */
@Data
public class JsonMessageType {
    /**
     * 业务单号
     */
    private String billno;

    /**
     * 货主编码
     */
    private String owner;
}
