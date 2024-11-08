package com.simplicode.portfolio.helper;

import com.simplicode.portfolio.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHelper {

    public Long getRequestUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getPrincipal() instanceof User user
           ? user.getId()
           : null;
    }

}
