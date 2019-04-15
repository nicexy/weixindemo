package com.example.entity;

import java.io.Serializable;
import java.util.List;

/***
 * 菜单 具体参数查看微信公众号
 * @author yx
 *
 */
public class Menu implements Serializable {

    private List<Button> button;//button sub_button

    public Menu(List<Button> button) {
        this.button = button;
    }

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }
}
