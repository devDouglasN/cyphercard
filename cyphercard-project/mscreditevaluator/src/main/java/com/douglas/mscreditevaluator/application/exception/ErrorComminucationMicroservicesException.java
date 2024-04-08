package com.douglas.mscreditevaluator.application.exception;

import lombok.Getter;

public class ErrorComminucationMicroservicesException extends Exception{

    @Getter
    private Integer status;

    public ErrorComminucationMicroservicesException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
