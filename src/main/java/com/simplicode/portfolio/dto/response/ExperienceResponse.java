package com.simplicode.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ExperienceResponse {

    private Long id;
    private String jobTitle;
    private String companyName;
    private Boolean isStillInRole;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
