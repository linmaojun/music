package com.music;

import com.music.util.MessageUtil;
import net.dongliu.requests.Requests;
import org.junit.Test;

/**
 * @author linmaojun
 * 类名称: Text
 * 类描述:
 * 创建时间: 2019年03月13日  8:08
 */
public class Text {

    @Test
    public void testMsg() {
        String token = MessageUtil.getAccessToken();
        System.out.println(token);
    }
}
