package com.music.vo.button;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linmaojun
 * 类名称: Button
 * 类描述:
 * 创建时间: 2019年03月14日  21:42
 */
public class Button {
    /**
     * 多个菜单按钮
     */
    private List<ChildrenButton> button = new ArrayList<>();

    public List<ChildrenButton> getButton() {
        return button;
    }

    public void setButton(List<ChildrenButton> button) {
        this.button = button;
    }
}
