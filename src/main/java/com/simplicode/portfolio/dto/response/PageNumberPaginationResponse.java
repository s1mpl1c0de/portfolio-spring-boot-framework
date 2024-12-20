package com.simplicode.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageNumberPaginationResponse<T> {

    private Integer count;
    private String next;
    private String previous;
    private List<T> results;

}
