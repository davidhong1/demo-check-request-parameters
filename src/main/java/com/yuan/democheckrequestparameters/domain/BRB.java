package com.yuan.democheckrequestparameters.domain;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: mac
 * @date: 2019-01-23
 * @description: BaseResponseBean
 */
@Data
public class BRB {

    private static final SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 状态
     */
    private Integer status;

    /**
     * 消息
     */
    private String msg;

    /**
     * 内容
     */
    private Object content;

    /**
     * 时间
     */
    private String timestamp;

    public BRB(BRStatus brStatus, Object content) {
        this.status = brStatus.getStatus();
        this.msg = brStatus.getMsg();
        this.content = content;
        this.timestamp = sdf4.format(new Date());
    }

    public BRB(BRStatus brStatus) {
        this.status = brStatus.getStatus();
        this.msg = brStatus.getMsg();
        this.timestamp = sdf4.format(new Date());
    }

    public BRB(int status, String message, Object content) {
        this.status = status;
        this.msg = message;
        this.content = content;
    }

}
