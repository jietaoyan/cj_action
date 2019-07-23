package com.bccm.projectservices.entity.VO;

import java.util.List;

//用于sqlserver分页，向前台传送数据
public class PageEntity<T> {

    private long totalElements;
    private List<T> content;

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
