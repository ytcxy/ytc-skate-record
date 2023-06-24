package com.ytc.skate.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.ytc.skate.common.CommonResp;
import com.ytc.skate.model.req.AddTrickInfoReq;
import com.ytc.skate.model.req.TrickInfoReq;
import com.ytc.skate.model.resp.TrickInfoResp;
import com.ytc.skate.model.vo.TrickInfoVO;
import com.ytc.skate.sys.entity.Trick;
import com.ytc.skate.sys.service.ITrickService;
import com.ytc.skate.util.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2023-06-23
 */
@Slf4j
@RestController
@RequestMapping("/trick")
public class TrickController {



    @Autowired
    private ITrickService trickService;


    @PostMapping("/add")
    public CommonResp<Boolean> addTrickInfo(@RequestBody AddTrickInfoReq req) {
        try {
            Trick trick = new Trick();
            trick.setUserId(req.getUserId());
            trick.setName(req.getName());
            trick.setTrickName(req.getTrickName());
            trick.setTrickId(req.getTrickId());
            trick.setCount(req.getCount());
            boolean save = trickService.save(trick);
            return CommonResp.success(save);
        } catch (Exception e) {
            log.error("addTrickInfo fail", e);
        }

        return CommonResp.fail();
    }



    @PostMapping("/info")
    public CommonResp<TrickInfoResp> getTrickInfo(@RequestBody TrickInfoReq trickInfoReq) {
        if (!TrickInfoReq.valid(trickInfoReq)) {
            return CommonResp.paramError();
        }

        // 补足结束时间
        if (Objects.isNull(trickInfoReq.getEndTime())) {
            trickInfoReq.setEndTime(String.valueOf(DateTimeUtils.dateTime2MilliSec(LocalDateTime.now())));
        }

        LambdaQueryWrapper<Trick> query = new LambdaQueryWrapper<>();
        query.eq(Trick::getUserId, trickInfoReq.getUserId());
        query.ge(Trick::getAddTime, DateTimeUtils.long2LocalDateTime(Long.valueOf(trickInfoReq.getBeginTime())));
        query.le(Trick::getAddTime, DateTimeUtils.long2LocalDateTime(Long.valueOf(trickInfoReq.getEndTime())));
        List<Trick> tricks = trickService.list(query);

        Map<Integer, List<Trick>> id2Trick= tricks.stream().collect(Collectors.groupingBy(Trick::getTrickId));
        List<Trick> result = Lists.newArrayList();
        for (Map.Entry<Integer, List<Trick>> entry : id2Trick.entrySet()) {
            result.add(entry.getValue().stream().reduce(TrickController::mergeTrick).get());
        }
        result = result.stream().sorted((o1, o2) -> {
            return o2.getCount().intValue() - o1.getCount().intValue();
        }).collect(Collectors.toList());

        TrickInfoResp resp = new TrickInfoResp();
        resp.setTrickList(result.stream().map(trick -> {
            TrickInfoVO trickInfoVO = new TrickInfoVO();
            trickInfoVO.setTrickName(trick.getTrickName());
            trickInfoVO.setTrickId(trick.getTrickId());
            trickInfoVO.setUserId(trick.getUserId());
            trickInfoVO.setCount(trick.getCount());
            return trickInfoVO;
        }).collect(Collectors.toList()));
        return CommonResp.success(resp);
    }

    private static Trick mergeTrick(Trick trick, Trick trick1) {
        trick.setCount(trick.getCount() + trick1.getCount());
        return trick;
    }

}
