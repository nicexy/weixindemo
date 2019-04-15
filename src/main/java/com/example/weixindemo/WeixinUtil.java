package com.example.weixindemo;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@RestController
@RequestMapping("/test")
public class WeixinUtil {
        @RequestMapping("/geturl")
        @ResponseBody
        public String geturl(){
            System.out.println("进来");
            return "111";
        }
        /*
         * 自定义token, 用作生成签名,从而验证安全性
         * */
        private final String TOKEN = "yxtest";
        @RequestMapping("/getWeixin")
        @ResponseBody
        public String testWeixin(@RequestParam(value = "signature",defaultValue = "") String signature,
                               @RequestParam(value = "timestamp",defaultValue = "") String timestamp,
                               @RequestParam(value = "nonce",defaultValue = "") String nonce,
                               @RequestParam(value = "echostr",defaultValue = "") String echostr){
            System.out.println("-----开始校验签名-----");

            /**
             * 将token、timestamp、nonce三个参数进行字典序排序
             * 并拼接为一个字符串
             */
            String sortStr = sort(TOKEN,timestamp,nonce);
            /**
             * 字符串进行shal加密
             */
            String mySignature = shal(sortStr);
            /**
             * 校验微信服务器传递过来的签名 和  加密后的字符串是否一致, 若一致则签名通过
             */
            if(!"".equals(signature) && !"".equals(mySignature) && signature.equals(mySignature)){
                System.out.println("-----签名校验通过-----");
                return echostr;
            }else {
                System.out.println("-----校验签名失败-----");
            }
            return null;
        }

        /**
         * 参数排序
         * @param token
         * @param timestamp
         * @param nonce
         * @return
         */
        public String sort(String token, String timestamp, String nonce) {
            String[] strArray = {token, timestamp, nonce};
            Arrays.sort(strArray);
            StringBuilder sb = new StringBuilder();
            for (String str : strArray) {
                sb.append(str);
            }
            return sb.toString();
        }

        /**
         * 字符串进行shal加密
         * @param str
         * @return
         */
        public String shal(String str){
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                digest.update(str.getBytes());
                byte messageDigest[] = digest.digest();

                StringBuffer hexString = new StringBuffer();
                // 字节数组转换为 十六进制 数
                for (int i = 0; i < messageDigest.length; i++) {
                    String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                    if (shaHex.length() < 2) {
                        hexString.append(0);
                    }
                    hexString.append(shaHex);
                }
                return hexString.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "";
        }
    }


