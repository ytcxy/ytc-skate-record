package com.ytc.skate.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TrickIDetailVO implements Serializable {

    private String userId;

    private String trickName;

    private Long count;

    private Integer trickId;
}
