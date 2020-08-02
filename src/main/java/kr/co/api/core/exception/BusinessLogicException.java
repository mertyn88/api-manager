package kr.co.api.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 비즈니스 로직 Exception
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessLogicException extends Exception {

    private String errorCode;
    private String description  = "";
    private String errorMsg     = "";
    private String errorMsg2    = "";

    public BusinessLogicException(ExceptionCode exceptionCode) {
        this.errorCode      = exceptionCode.getCode();
        this.description    = exceptionCode.getDescription();
    }

    public BusinessLogicException(ExceptionCode exceptionCode, String errorMsg) {
        this.errorCode      = exceptionCode.getCode();
        this.description    = exceptionCode.getDescription();
        this.errorMsg       = errorMsg;
    }

    public BusinessLogicException(ExceptionCode exceptionCode, String errorMsg, String errorMsg2) {
        this.errorCode      = exceptionCode.getCode();
        this.description    = exceptionCode.getDescription();
        this.errorMsg       = errorMsg;
        this.errorMsg2		= errorMsg2;
    }

    public <T extends Number> BusinessLogicException(ExceptionCode exceptionCode, T errorMsg) {
        this.errorCode      = exceptionCode.getCode();
        this.description    = exceptionCode.getDescription();
        this.errorMsg       = String.valueOf(errorMsg);
    }

    public <T extends Number> BusinessLogicException(ExceptionCode exceptionCode, String errorMsg, T errorMsg2) {
        this.errorCode      = exceptionCode.getCode();
        this.description    = exceptionCode.getDescription();
        this.errorMsg       = errorMsg;
        this.errorMsg2		= String.valueOf(errorMsg2);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
