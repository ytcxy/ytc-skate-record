package com.ytc.skate.model.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class TrickInfoReq implements Serializable {

    private String userId;
    private String beginTime;
    private String endTime;

    public static boolean valid(TrickInfoReq req) {
        if (Objects.isNull(req) || Objects.isNull(req.getUserId()) || Objects.isNull(req.getBeginTime())) {
            return false;
        }
        return true;
    }
}
