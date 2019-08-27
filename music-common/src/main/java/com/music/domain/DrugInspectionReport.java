package com.music.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author linmaojun
 * 类名称: DrugInspectionReport
 * 类描述:
 * 创建时间: 2019年07月19日  15:57
 */
@Data
public class DrugInspectionReport {
    /**
     *  文件序列号
     */
    @JSONField(ordinal = 1)
    private String serial;

    /**
     * 货品编码【货主】】
     */
    @JSONField(ordinal = 2)
    private String goodsid;

    /**
     * 件装量
     */
    @JSONField(ordinal = 3)
    private String packSize;

    /**
     * 批号
     */
    @JSONField(ordinal = 4)
    private String lotno;

    /**
     * 业务类型
     */
    @JSONField(ordinal = 5)
    private String businessType;

    /**
     * 文件发送方系统
     */
    @JSONField(ordinal = 6)
    private String sender;

    /**
     * 发送日期
     */
    @JSONField(ordinal = 7)
    private String sendtime;

}
