package com.yuan.democheckrequestparameters.domain;

import lombok.Getter;
import lombok.Setter;

public enum BRStatus {

    SUCCESS(200, "SUCCESS"),
    PARAS_ERROR(732, "参数有误");

    @Setter
    @Getter
    private final int status;
    @Setter
    @Getter
    private final String msg;

    BRStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
