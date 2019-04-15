package com.example.weixindemo;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.Button;
import com.example.entity.Menu;
import org.apache.commons.httpclient.NameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuUtil {
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    JSONObject json = TokenUtil.getToken();
    String accessToken = json.getString("access_token");
    String expires_in = json.getString("expires_in");
    @RequestMapping("/createMenu")
    public void createMenu(){
        List<Button> buttons = new ArrayList<Button>();
        Button btn = new Button("click","科","click1");

        List<Button> list = new ArrayList<Button>();
        Button view1 = new Button("view","百度搜索");
        view1.setUrl("http://www.baidu.com");
        Button min = new Button("miniprogram","min");
        min.setUrl("http://mp.weixin.qq.com");
        min.setAppid("wx286b93c14bbf93aa");
        list.add(view1);
        list.add(min);
        Button btn1 = new Button("二级菜单",list);
        buttons.add(btn);
        buttons.add(btn1);
        Menu menu = new Menu(buttons);

        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 调用接口创建菜单

        JSONObject jsonObject = TokenUtil.doPost(url,menu);
        String errcode = jsonObject.getString("errcode");
        if("0".equals(errcode)){
            System.out.println("创建成功");
        }

    }
}
