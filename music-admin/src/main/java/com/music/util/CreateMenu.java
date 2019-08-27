package com.music.util;

import com.music.vo.button.*;
import net.dongliu.requests.Requests;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linmaojun
 * 类名称: CreateMenu
 * 类描述:
 * 创建时间: 2019年03月15日  16:07
 */
public class CreateMenu {
    /**
     * 创建菜单
     */
    public static void createMenu(){
        //菜单对象
        Button btn = new Button();
        //创建第一个一级菜单
        ClickButton clickButton =  new ClickButton("一级点击", "1");
        //创建第二个一级菜单
        ViewButton viewButton = new ViewButton("一级跳转", "http://www.baidu.com");
        //创建第三个一级菜单
        SubButton subButton = new SubButton("有子菜单");

        List<ChildrenButton> childrenButtonList = new ArrayList<>();
        childrenButtonList.add(new PhotoOrAlbumButton("传图", "31"));
        childrenButtonList.add(new ClickButton("点击", "32"));
        childrenButtonList.add(new ViewButton("网易新闻", "http://news.163.com"));
        //为第三个一级菜单增加子菜单
        subButton.setSub_button(childrenButtonList);

        List<ChildrenButton> buttonList = new ArrayList<>();
        buttonList.add(clickButton);
        buttonList.add(viewButton);
        buttonList.add(subButton);

        btn.setButton(buttonList);

        //转为json
        JSONObject jsonObject = JSONObject.fromObject(btn);
        //准备url
        String url = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        String accessToken = MessageUtil.getAccessToken();
        url = url.replace("ACCESS_TOKEN",accessToken );
        String result = Requests.post(url).forms().jsonBody(jsonObject).send().readToText();
        System.out.println(accessToken);
        System.out.println(jsonObject);
        System.out.println(result);
    }

    public static void main(String[] args) {
        createMenu();
    }
}
