package com.ytc.skate.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.ytc.skate.common.CommonResp;
import com.ytc.skate.model.bo.WxLoginBO;
import com.ytc.skate.model.resp.WxUserResp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/wx")
@RestController
public class UserController {

    @GetMapping("/login")
    public CommonResp<WxUserResp> login(@RequestParam(value = "code") String code) {

        String appId = "wx302c008de186e2dc";
        String appSecret = "27bd1f03cac014e63828bf5c9678f64c";
        String grant_type = "authorization_code";
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+ "&secret="+appSecret
                +"&js_code="+ code +"&grant_type=authorization_code";
        WxUserResp wxUserResp = new WxUserResp();
        try {
            Request request = new Request.Builder().url(url).build();
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            // 3.发起请求与接收返回值
            Response response = okHttpClient.newCall(request).execute();
            String resultData = response.body().string();
            WxLoginBO wxLoginBO = JSONObject.parseObject(resultData, WxLoginBO.class);
            wxUserResp.setUserId(wxLoginBO.getOpenid());
            wxUserResp.setToken(wxLoginBO.getSession_key());
            System.out.println("从服务端返回结果: " + resultData);
        } catch (Exception e) {
            log.error("wxLogin error ", e);
            return CommonResp.fail();
        }

        return CommonResp.success(wxUserResp);
    }
}
