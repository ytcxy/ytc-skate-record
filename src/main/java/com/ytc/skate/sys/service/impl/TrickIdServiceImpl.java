package com.ytc.skate.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ytc.skate.model.resp.TrickIdInfoResp;
import com.ytc.skate.sys.entity.TrickId;
import com.ytc.skate.sys.mapper.TrickIdMapper;
import com.ytc.skate.sys.service.ITrickIdService;
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
 * @since 2023-06-23
 */
@Service
public class TrickIdServiceImpl extends ServiceImpl<TrickIdMapper, TrickId> implements ITrickIdService {

    @Resource
    private TrickIdMapper trickIdMapper;
    @Override
    public List<TrickIdInfoResp> info(String userId) {
        LambdaQueryWrapper<TrickId> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrickId::getUserId, userId);
        List<TrickId> trickIds = trickIdMapper.selectList(queryWrapper);
        return trickIds.stream().map(trick -> {
            TrickIdInfoResp trickInfo = new TrickIdInfoResp();
            trickInfo.setTrick(trick.getTrick());
            trickInfo.setTrickId(trick.getId());
            return trickInfo;
        }).collect(Collectors.toList());
    }
}
