package com.ytc.skate.model.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddTrickInfoReq implements Serializable {

    private String userId;

    private String name;

    private String trickName;

    private Long count;

    private Integer trickId;

}
