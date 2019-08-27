package com.music.model;

import lombok.Data;

/**
 * @author linmaojun
 * 类名称: Ftp
 * 类描述:
 * 创建时间: 2019年05月31日  13:51
 */
@Data
public class Ftp {
    /**
     * ip地址
     */
    private String ipAddr;

    /**
     * 端口号
     */
    private Integer port;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 路径
     */
    private String path;

    public Ftp(String ipAddr, Integer port, String userName, String pwd, String path) {
        this.ipAddr = ipAddr;
        this.port = port;
        this.userName = userName;
        this.pwd = pwd;
        this.path = path;
    }

    public Ftp() {
    }
}
