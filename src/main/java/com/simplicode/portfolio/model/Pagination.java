package com.simplicode.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class Pagination {

    private Integer page;
    private Integer size;
    private String url;
    private Integer count;

    public Integer getLimit() {
        return size;
    }

    public Integer getOffset() {
        return (page - 1) * size;
    }

    public String getNext() {
        return (getOffset() + size < count) ? String.format("%s?page=%d", url, page + 1) : null;
    }

    public String getPrevious() {
        return (page > 1) ? String.format("%s?page=%d", url, page - 1) : null;
    }

}
