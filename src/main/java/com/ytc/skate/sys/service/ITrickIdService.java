package com.ytc.skate.sys.service;

import com.ytc.skate.model.resp.TrickIdInfoResp;
import com.ytc.skate.sys.entity.TrickId;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-06-23
 */
public interface ITrickIdService extends IService<TrickId> {

    List<TrickIdInfoResp> info(String userId);



}
