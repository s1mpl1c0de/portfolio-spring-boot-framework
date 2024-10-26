package com.simplicode.portfolio.validation;

import com.simplicode.portfolio.exception.AlreadyExistsException;
import com.simplicode.portfolio.model.User;
import com.simplicode.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final UserRepository userRepository;

    public void validateUniqueUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new AlreadyExistsException("Username already exists");
        }
    }

}
