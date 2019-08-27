package com.music.domain.transport;

import lombok.Data;

/**
 * @author linmaojun
 * 类名称: TransportProduct
 * 类描述:
 * 创建时间: 2019年07月18日  15:21
 */
@Data
public class TransportProduct {
    /**
     * 货品-通用名
     */
    private String commonname;

    /**
     * 货品编码【货主】
     */
    private String goodsid;

    /**
     * 货品-商品名
     */
    private String goodsname;

    /**
     * 品规
     */
    private String goodstype;

    /**
     * 批号
     */
    private String lotno;

    /**
     * 件装量
     */
    private String packsize;

    /**
     * 细单号2
     */
    private String placesupplydtlid;

    /**
     * 交易单位数量
     */
    private String qty;

    /**
     * 交易单位
     */
    private String tradegoodspack;
}
