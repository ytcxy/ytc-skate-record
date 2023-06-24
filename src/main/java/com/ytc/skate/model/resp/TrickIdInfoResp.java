package com.ytc.skate.model.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class TrickIdInfoResp implements Serializable {

    private String trick;

    private Integer trickId;

}
