package com.ytc.skate.common;

import lombok.Data;

/**
 * @author yutao29
 */
@Data
public class CommonResp<T> {

    private static final String PARAM_ERROR = "参数错误";

    private static final int SUCCESS = 200;
    private int code;
    private String msg;
    private T data;

    public boolean isSuccess() {
        return this.code == SUCCESS && this.data != null;
    }

    public static <T> CommonResp<T> fail() {
        CommonResp<T> resp = new CommonResp<>();
        resp.setMsg("sysErr");
        resp.setCode(-1);
        return resp;
    }

    public static <T> CommonResp<T> fail(int code) {
        CommonResp<T> resp = new CommonResp<>();
        resp.setMsg("sysErr");
        resp.setCode(code);
        return resp;
    }
    public static <T> CommonResp<T> paramError() {
        CommonResp<T> resp = new CommonResp<>();
        resp.setMsg(PARAM_ERROR);
        resp.setCode(400);
        return resp;
    }
    public static <T> CommonResp<T> fail(int code, String msg) {
        CommonResp<T> resp = new CommonResp<>();
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public static <T> CommonResp<T> success(T data) {
        CommonResp<T> resp = new CommonResp<>();
        resp.setData(data);
        resp.setCode(SUCCESS);
        resp.setMsg("success");
        return resp;
    }
    public static <T> CommonResp<T> success() {
        CommonResp<T> resp = new CommonResp<>();
        resp.setCode(SUCCESS);
        resp.setMsg("success");
        return resp;
    }

}
