package com.ytc.skate.model.bo;

import lombok.Data;

@Data
public class WxLoginBO {

    private String openid;
    private String session_key;
    private String errmsg;
    private String unionid;
    private Integer errcode;
}
