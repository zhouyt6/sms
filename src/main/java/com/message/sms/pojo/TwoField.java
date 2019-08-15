package com.message.sms.pojo;

/**
 * @Author: ZYT
 * @Date: 2019/08/06
 * @Description:
 */

public class TwoField<T, U> {

    private T t;

    private U u;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public U getU() {
        return u;
    }

    public void setU(U u) {
        this.u = u;
    }
}
