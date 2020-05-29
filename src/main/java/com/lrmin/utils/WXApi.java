package com.lrmin.utils;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author lirongmin
 * @date 2020/5/26 0026
 */
public class WXApi {
    private static Logger log = LoggerFactory.getLogger(WXApi.class);

    private static String APP_ID = "wx246c109a2a0f1065";
    private static String APP_SECRET = "fabd0d35280f3387c2f09e200e2c6109";

    public static String getOpenIdByCode(String code){
        //请求路径
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        //使用Restemplate来发送HTTP请求
        RestTemplate restTemplate = new RestTemplate();
        // json对象
        JSONObject jsonObject = new JSONObject();

        // LinkedMultiValueMap 有点像JSON，用于传递post数据，网络上其他教程都使用
        // MultiValueMpat<>来传递post数据
        // 但传递的数据类型有限，不能像这个这么灵活，可以传递多种不同数据类型的参数
        LinkedMultiValueMap body=new LinkedMultiValueMap();
        body.add("appid",APP_ID);
        body.add("secret",APP_SECRET);
        body.add("js_code",code);
        body.add("grant_type","authorization_code");

        //设置请求header 为 APPLICATION_FORM_URLENCODED
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 请求体，包括请求数据 body 和 请求头 headers
        HttpEntity httpEntity = new HttpEntity(body,headers);


        try {
            //使用 exchange 发送请求，以String的类型接收返回的数据
            //ps，我请求的数据，其返回是一个json
            ResponseEntity<String> strbody = restTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
            //解析返回的数据
            JSONObject jsTemp = new JSONObject(strbody.getBody());

            log.info("请求到openid： "+ (String) jsTemp.get("openid"));

            return (String) jsTemp.get("openid");

        }catch (Exception e){
            System.out.println(e);
        }
        return "";
    }
}
