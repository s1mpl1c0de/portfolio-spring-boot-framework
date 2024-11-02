package com.simplicode.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Page {

    private Integer count;
    private String requestUrl;
    private Integer pageNumber;
    private Integer pageSize;

    public Integer getLimit() {
        return pageSize;
    }

    public Integer getOffset() {
        return (pageNumber - 1) * pageSize;
    }

    public String getNext() {
        return (getOffset() + pageSize < count)
           ? String.format("%s?page=%d", requestUrl, pageNumber + 1)
           : null;
    }

    public String getPrevious() {
        return (pageNumber > 1)
           ? String.format("%s?page=%d", requestUrl, pageNumber - 1)
           : null;
    }

}
