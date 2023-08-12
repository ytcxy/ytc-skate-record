package com.ytc.skate.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.ytc.skate.common.CommonResp;
import com.ytc.skate.model.req.AddTrickInfoReq;
import com.ytc.skate.model.req.TrickInfoReq;
import com.ytc.skate.model.resp.TrickInfoResp;
import com.ytc.skate.model.vo.TrickInfoVO;
import com.ytc.skate.sys.entity.TrickDetail;
import com.ytc.skate.sys.service.ITrickDetailService;
import com.ytc.skate.util.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
 * @since 2023-08-06
 */
@Slf4j
@RestController
@RequestMapping("/trickDetail")
public class TrickDetailController {

    @Autowired
    private ITrickDetailService trickService;


    @PostMapping("/add")
    public CommonResp<Boolean> addTrickInfo(@RequestBody AddTrickInfoReq req) {
        try {
            TrickDetail trick = new TrickDetail();
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

        LambdaQueryWrapper<TrickDetail> query = new LambdaQueryWrapper<>();
        query.eq(TrickDetail::getUserId, trickInfoReq.getUserId());
        query.ge(TrickDetail::getAddTime, DateTimeUtils.long2LocalDateTime(Long.valueOf(trickInfoReq.getBeginTime())));
        query.le(TrickDetail::getAddTime, DateTimeUtils.long2LocalDateTime(Long.valueOf(trickInfoReq.getEndTime())));
        List<TrickDetail> tricks = trickService.list(query);

        Map<Integer, List<TrickDetail>> id2Trick= tricks.stream().collect(Collectors.groupingBy(TrickDetail::getTrickId));
        List<TrickDetail> result = Lists.newArrayList();
        for (Map.Entry<Integer, List<TrickDetail>> entry : id2Trick.entrySet()) {
            result.add(entry.getValue().stream().reduce(TrickDetailController::mergeTrick).get());
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

    private static TrickDetail mergeTrick(TrickDetail trick, TrickDetail trick1) {
        trick.setCount(trick.getCount() + trick1.getCount());
        return trick;
    }

}
