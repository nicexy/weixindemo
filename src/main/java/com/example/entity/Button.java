package com.example.entity;

import java.io.Serializable;
import java.util.List;

/***
 * 按钮 具体参数查看微信公众号
 * @author yx
 *
 */
public class Button implements Serializable {
    private String type;//菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
    private String name;//s菜单标题，不超过16个字节，子菜单不超过60个字节
    private String key;//click等点击类型必须	菜单KEY值，用于消息接口推送
    private String url;//view、miniprogram类型必须	网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
    private String media_id;//media_id类型和view_limited类型必须	调用新增永久素材接口返回的合法media_id
    private String appid;//小程序的appid
    private String pagepath;//小程序的页面路径
    private List<Button> sub_button;

    public Button(){

    }
    public Button(String type, String name) {
        this.type = type;
        this.name = name;
    }
    public Button(String type, String name, String key) {
        this.type = type;
        this.name = name;
        this.key = key;
    }

    public Button( String name, List<Button> sub_button) {
        this.name = name;
        this.sub_button = sub_button;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public List<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Button> sub_button) {
        this.sub_button = sub_button;
    }
}
