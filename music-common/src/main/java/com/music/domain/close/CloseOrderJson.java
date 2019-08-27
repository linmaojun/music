package com.music.domain.close;

import lombok.Data;

/**
 * @author linmaojun
 * 类名称: CloseOrderJson
 * 类描述:
 * 创建时间: 2019年07月18日  11:18
 */
@Data
public class CloseOrderJson {
    /**
     * 数据信息
     */
    private Content content;
    /**
     * 接口信息
     */
    private Header header;
}
