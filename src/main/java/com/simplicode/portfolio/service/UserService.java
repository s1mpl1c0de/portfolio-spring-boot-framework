package com.simplicode.portfolio.service;

import com.simplicode.portfolio.dto.request.UserCreateRequest;
import com.simplicode.portfolio.dto.request.UserUpdateRequest;
import com.simplicode.portfolio.dto.response.GlobalResponse;
import com.simplicode.portfolio.dto.response.UserResponse;
import com.simplicode.portfolio.exception.NotFoundException;
import com.simplicode.portfolio.model.User;
import com.simplicode.portfolio.repository.UserRepository;
import com.simplicode.portfolio.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserValidation userValidation;

    public void save(UserCreateRequest userCreateRequest) {
        userValidation.validateUniqueUsername(userCreateRequest.getUsername());

        User user = modelMapper.map(userCreateRequest, User.class)
           .setPassword(passwordEncoder.encode(userCreateRequest.getPassword()))
           .setIsEnabled(true);

        userRepository.save(user);
    }

    public GlobalResponse findAll() {
        List<UserResponse> userResponses = userRepository.findAll().stream()
           .map(user -> modelMapper.map(user, UserResponse.class)
              .setFullName(user.getFullName())
           ).toList();

        return new GlobalResponse()
           .setCount(userResponses.size())
           .setResults(userResponses);
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
           .orElseThrow(() -> new NotFoundException("User not found"));

        return modelMapper.map(user, UserResponse.class);
    }

    public void updateById(Long id, UserUpdateRequest userUpdateRequest) {
        UserResponse userResponse = findById(id);
        User user = modelMapper.map(userUpdateRequest, User.class);
        userRepository.updateById(userResponse.getId(), user);
    }

    public Long getRequestUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User user)) {
            return null;
        }

        return user.getId();
    }

}
