package com.ytc.skate.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ytc.skate.model.resp.TrickIdInfoResp;
import com.ytc.skate.sys.entity.UserTrick;
import com.ytc.skate.sys.mapper.UserTrickMapper;
import com.ytc.skate.sys.service.IUserTrickService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-08-06
 */
@Service
public class UserTrickServiceImpl extends ServiceImpl<UserTrickMapper, UserTrick> implements IUserTrickService {
    @Resource
    private UserTrickMapper userTrickMapper;
    @Override
    public List<TrickIdInfoResp> info(String userId) {
        LambdaQueryWrapper<UserTrick> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTrick::getUserId, userId);
        List<UserTrick> trickIds = userTrickMapper.selectList(queryWrapper);
        return trickIds.stream().map(trick -> {
            TrickIdInfoResp trickInfo = new TrickIdInfoResp();
            trickInfo.setTrick(trick.getTrick());
            trickInfo.setTrickId(trick.getId());
            return trickInfo;
        }).collect(Collectors.toList());
    }
}
