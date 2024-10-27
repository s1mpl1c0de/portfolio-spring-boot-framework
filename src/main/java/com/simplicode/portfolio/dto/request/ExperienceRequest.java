package com.simplicode.portfolio.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ExperienceRequest {

    @NotBlank
    private String jobTitle;

    @NotBlank
    private String companyName;

    @NotNull
    @Min(value = 1, message = "must be between 1 and 12")
    @Max(value = 12, message = "must be between 1 and 12")
    private Short startedMonth;

    @NotNull
    @Min(value = 1000, message = "must be a 4-digit number")
    @Max(value = 9999, message = "must be a 4-digit number")
    private Short startedYear;

    @NotNull
    private Boolean isStillInRole;

    @Min(value = 1, message = "must be between 1 and 12")
    @Max(value = 12, message = "must be between 1 and 12")
    private Short endedMonth;

    @Min(value = 1000, message = "must be a 4-digit number")
    @Max(value = 9999, message = "must be a 4-digit number")
    private Short endedYear;

    private String description;
    private Long userId;

}
