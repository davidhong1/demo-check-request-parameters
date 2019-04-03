package com.yuan.democheckrequestparameters.controller;

import com.yuan.democheckrequestparameters.domain.BRB;
import com.yuan.democheckrequestparameters.domain.BRStatus;
import com.yuan.democheckrequestparameters.domain.GoodReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * @Description:
 * @Author: mac
 * @Date: 2019-04-03 13:13
 * @Version: 0.1
 **/
@Slf4j
@Validated
@RestController
@RequestMapping("/good")
public class ParasCheckController {

    @GetMapping("/{goodNo}")
    public BRB good(@Pattern(regexp = "^[0-9]{6}$", message = "商品编号不正确") @PathVariable("goodNo") String goodNo) {
        log.info("TAG=good, METHOD=get, goodNo={}", goodNo);
        return new BRB(BRStatus.SUCCESS, "获取商品goodNo=" + goodNo + "信息");
    }

    @PostMapping
    public BRB good(@RequestBody @Valid GoodReq goodReq, BindingResult bindingResult) {
        log.info("TAG=good, METHOD=post, goodNo={}, goodName={}, goodPrice={}", goodReq.getGoodNo(), goodReq.getGoodName(), goodReq.getGoodPrice());
        return new BRB(BRStatus.SUCCESS, "增加新商品成功");
    }

}
