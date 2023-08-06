package com.ytc.skate.sys.service;

import com.ytc.skate.model.resp.TrickIdInfoResp;
import com.ytc.skate.sys.entity.UserTrick;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-08-06
 */
public interface IUserTrickService extends IService<UserTrick> {

    List<TrickIdInfoResp> info(String userId);


}
