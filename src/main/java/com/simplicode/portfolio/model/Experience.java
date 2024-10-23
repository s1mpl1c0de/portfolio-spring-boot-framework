package com.simplicode.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Experience {

    private Long id;
    private String jobTitle;
    private String companyName;
    private Short startedMonth;
    private Short startedYear;
    private Short endedMonth;
    private Short endedYear;
    private Boolean isStillInRole;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
