package com.hero.ms.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author yejx
 * @date 2019-5-18 22:37
 */
@Data
public class PageResult<T> {
    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

    /**
     * 总条数
     */
    private Integer totalRows;

    /**
     * 结果数据集
     */
    private List<T> data;

    public PageResult() {
    }

    public PageResult(Integer pageNo, Integer pageSize, List<T> data, Integer totalRows) {
        this.pageAnalyze(pageNo, pageSize);
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.data = data;
        this.totalRows = totalRows;
    }

    private void pageAnalyze(Integer pageNo, Integer pageSize) {
        if (pageNo <= 0) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
        if (pageSize <= 0) {
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }
    }
}
