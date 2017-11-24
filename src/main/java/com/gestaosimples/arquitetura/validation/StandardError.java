package com.gestaosimples.arquitetura.validation;

import java.io.Serializable;
import org.springframework.http.HttpStatus;

public class StandardError implements Serializable {

    /**  */
    private static final long serialVersionUID = -8061761562071215986L;

    private Integer status;
    private String msg;
    private Long timeStamp;

    @Override
    public String toString() {
        return super.toString();
    }

    public StandardError(HttpStatus status, String msg, Long timeStamp) {
        super();
        this.status = status.value();
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
