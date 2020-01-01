package com.hero.ms.common.pojo;

import lombok.Data;

/**
 * 服务结果
 */
@Data
public class ServiceResult {
    private boolean SUCCESS;   //是否成功
    private String STATUS;   //返回码
    private String MSG; //返回信息
    private Object DATA;    //返回数据

    public ServiceResult() {
        this.STATUS = StatusCode.DEFAULT.getKey();
    }

    /**
     * 适用于增删改
     * @param SUCCESS
     * @param STATUS
     * @param MSG
     */
    public ServiceResult(boolean SUCCESS, String STATUS, String MSG) {
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
    public ServiceResult(boolean SUCCESS, String STATUS, String MSG, Object DATA) {
        this.SUCCESS = SUCCESS;
        this.STATUS = STATUS;
        this.MSG = MSG;
        this.DATA = DATA;
    }
}
