package com.example.weixindemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.httpclient.NameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/token")
public class TokenUtil {
    private static final String appid="wx0e56fcff49e02272";//测试id
    private static final String appsecret="221e1a8a08b7e5823b9580863e2dc51f";

    @RequestMapping("/getToken")
    public static JSONObject getToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
        return doGet(url);
    }
    /**
     * get请求
     */
    public static JSONObject doGet(String url){
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        try {
            int code = client.executeMethod(getMethod);

            if(code==200){//请求成功
                String result = getMethod.getResponseBodyAsString();
                JSONObject json = JSON.parseObject(result);
                System.out.println(json);
               return json;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求
     * @param url
     * @return
     */
    public static JSONObject doPost(String url,Object obj){
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);

        try {
            RequestEntity se = new StringRequestEntity(JSONObject.toJSON(obj).toString(), "application/json", "UTF-8");
            postMethod.setRequestEntity(se);
            int code = client.executeMethod(postMethod);
            if(code==200){//请求成功
                String result = postMethod.getResponseBodyAsString();
                JSONObject json = JSON.parseObject(result);
                return json;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
