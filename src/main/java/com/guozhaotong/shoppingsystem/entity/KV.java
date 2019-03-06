package com.guozhaotong.shoppingsystem.entity;

/**
 * @author 郭朝彤
 * @date 2019/3/6.
 */
public class KV {
    private Object k;
    private Object v;

    public KV() {
    }

    public KV(Object k, Object v) {
        this.k = k;
        this.v = v;
    }

    public Object getK() {
        return k;
    }

    public void setK(Object k) {
        this.k = k;
    }

    public Object getV() {
        return v;
    }

    public void setV(Object v) {
        this.v = v;
    }


}
