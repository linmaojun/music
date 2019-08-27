package com.music.domain.transport;

import com.music.domain.close.Header;
import lombok.Data;

/**
 * @author linmaojun
 * 类名称: TransportJson
 * 类描述:
 * 创建时间: 2019年07月18日  15:04
 */
@Data
public class TransportJson {
    /**
     * 数据信息
     */
    private Content content;
    /**
     * 接口信息
     */
    private Header header;
}
