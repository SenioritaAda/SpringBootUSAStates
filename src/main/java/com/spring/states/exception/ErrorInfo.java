package com.spring.states.exception;

public class ErrorInfo {
    private String errorMessage;
    private Integer errorCode;

    public ErrorInfo(Integer errorCode, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

}
