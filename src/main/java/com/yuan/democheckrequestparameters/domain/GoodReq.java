package com.yuan.democheckrequestparameters.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Description:
 * @Author: mac
 * @Date: 2019-04-03 13:18
 * @Version: 0.1
 **/
@Data
public class GoodReq {

    @NotBlank(message = "商品编号不能为空")
    @Pattern(regexp = "^[0-9]{6}$", message = "商品编号不正确")
    private String goodNo;

    @NotBlank(message = "商品名不能为空")
    @Size(max = 20, message = "商品名不能大于20")
    private String goodName;

    @NotNull(message = "商品价格不能为空")
    private Integer goodPrice;

}
