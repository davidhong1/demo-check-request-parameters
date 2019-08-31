# demo-check-request-parameters
SpringBoot项目 接口参数检查demo 使用@Validated注解

### show my code
定义类GoodReq.java
```java
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
```

controller层，如果入参goodReq不符合上面注解定义内容，bindingResult会带着错误信息或者抛出异常
```java
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
```
