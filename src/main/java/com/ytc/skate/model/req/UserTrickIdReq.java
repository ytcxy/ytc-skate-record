package com.ytc.skate.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserTrickIdReq implements Serializable {

    private String userId;

    private String trickName;

}
