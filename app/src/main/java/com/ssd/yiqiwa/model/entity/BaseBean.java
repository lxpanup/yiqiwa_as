package com.ssd.yiqiwa.model.entity;

/**
 * 创建接收服务器返回数据的类
 *
 * @author Joe
 */
public class BaseBean<T> {

    /**
     * Code : 1
     * Msg : 操作成功
     * Data : null
     */

    /**
     * 状态码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 组合信息
     */
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
