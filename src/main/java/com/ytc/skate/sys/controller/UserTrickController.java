package com.ytc.skate.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ytc.skate.common.CommonResp;
import com.ytc.skate.model.req.UserTrickIdReq;
import com.ytc.skate.model.resp.TrickIdInfoResp;
import com.ytc.skate.sys.entity.UserTrick;
import com.ytc.skate.sys.service.IUserTrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2023-08-06
 */
@RestController
@RequestMapping("/userTrick")
public class UserTrickController {


    @Autowired
    private IUserTrickService trickIdService;

    @GetMapping("/info")
    public CommonResp<List<TrickIdInfoResp>> userInfo(@RequestParam(value = "userId") String userId) {
        List<TrickIdInfoResp> result =  trickIdService.info(userId);
        if (result != null) {
            return CommonResp.success(result);
        }
        return CommonResp.fail(-1, "查询失败");
    }

    @PostMapping("/add")
    public CommonResp<Object> addTrickIdInfo(@RequestBody UserTrickIdReq userTrick) {
        LambdaQueryWrapper<UserTrick> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTrick::getTrick, userTrick.getTrickName());
        queryWrapper.eq(UserTrick::getUserId, userTrick.getUserId());
        UserTrick one = trickIdService.getOne(queryWrapper);
        if (Objects.nonNull(one)) {
            return CommonResp.success();
        }
        UserTrick trickId = new UserTrick();
        trickId.setTrick(userTrick.getTrickName());
        trickId.setUserId(userTrick.getUserId());


        boolean save = trickIdService.save(trickId);
        return CommonResp.success(save);
    }
}
