package com.ytc.skate.model.resp;

import com.ytc.skate.model.vo.TrickInfoVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TrickInfoResp implements Serializable {

    private List<TrickInfoVO> trickList;


}
