package com.simplicode.portfolio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private Boolean isEnabled;

}
