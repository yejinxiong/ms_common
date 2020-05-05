package com.hero.ms.common.pojo;

import lombok.Data;

/**
 * 服务结果
 */
@Data
public class ResponseResult {
    /**
     * 是否成功
     */
    private Boolean SUCCESS;

    /**
     * 返回状态码
     */
    private Integer STATUS;

    /**
     * 返回信息
     */
    private String MSG;

    /**
     * 返回数据
     */
    private Object DATA;

    public ResponseResult() {
        this.SUCCESS = MsgCode.DEFAULT.getKey();
        this.STATUS = StatusCode.DEFAULT.getKey();
        this.MSG = MsgCode.DEFAULT.getDesc();
    }

    /**
     * 适用于增删改
     * @param SUCCESS
     * @param STATUS
     * @param MSG
     */
    public ResponseResult(Boolean SUCCESS, Integer STATUS, String MSG) {
        this.SUCCESS = SUCCESS;
        this.STATUS = STATUS;
        this.MSG = MSG;
    }

    /**
     * 适用于查询
     * @param SUCCESS
     * @param STATUS
     * @param MSG
     * @param DATA
     */
    public ResponseResult(Boolean SUCCESS, Integer STATUS, String MSG, Object DATA) {
        this.SUCCESS = SUCCESS;
        this.STATUS = STATUS;
        this.MSG = MSG;
        this.DATA = DATA;
    }
}
