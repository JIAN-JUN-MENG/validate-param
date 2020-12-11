package com.github.validate.util;

import java.io.Serializable;
import java.util.List;


/**
 * 分页返回类
 *
 * @author MENG
 * @version 2019/4/24
 */
public class PageResult<T> implements Serializable
{
    private static final long serialVersionUID = -976472471189121682L;

    private List<T> rows;

    private long total;

    private long totalPages;

    private long pageNo;

    private long pageSize;

    public List<T> getRows()
    {
        return this.rows;
    }

    public void setRows(List<T> rows)
    {
        this.rows = rows;
    }

    public long getTotal()
    {
        return this.total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public long getTotalPages()
    {
        return this.totalPages;
    }

    public void setTotalPages(long totalPages)
    {
        this.totalPages = totalPages;
    }

    public long getPageNo()
    {
        return this.pageNo;
    }

    public void setPageNo(long pageNo)
    {
        this.pageNo = pageNo;
    }

    public long getPageSize()
    {
        return this.pageSize;
    }

    public void setPageSize(long pageSize)
    {
        this.pageSize = pageSize;
    }
}
