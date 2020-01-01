package com.hero.ms.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author yejx
 * @date 2019-5-18 22:37
 */
@Data
public class Page<T> {
    private Integer pageno;
    private Integer pagesize;
    private List<T> data;
    private Integer totalsize;
    private Integer totalno;

    public Page() {
    }

    public Page(Integer pageno, Integer pagesize, List<T> data, Integer totalsize, Integer totalno) {
        this.pageno = pageno;
        this.pagesize = pagesize;
        this.data = data;
        this.totalsize = totalsize;
        this.totalno = totalno;
    }

    public Page(Integer pageno, Integer pagesize) {
        if (pageno <= 0) {
            this.pageno = 1;
        } else {
            this.pageno = pageno;
        }
        if (pagesize <= 0) {
            this.pagesize = 10;
        } else {
            this.pagesize = pagesize;
        }
    }
}
