package com.music.domain.close;

import com.music.domain.transport.TransportProduct;
import lombok.Data;

/**
 * @author linmaojun
 * 类名称: CloseOrderProduct
 * 类描述:
 * 创建时间: 2019年07月18日  10:21
 */
@Data
public class CloseOrderProduct extends TransportProduct {
    /**
     * 细单号1
     */
    private String billoid;

    /**
     * 细单号3
     */
    private String dtlmiscordnbr;

    /**
     * 生产日期
     */
    private String proddate;

    /**
     * 有效期
     */
    private String validdate;

}
