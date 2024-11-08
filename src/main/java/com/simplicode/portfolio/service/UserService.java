package com.simplicode.portfolio.service;

import com.simplicode.portfolio.dto.request.UserCreateRequest;
import com.simplicode.portfolio.dto.request.UserUpdateRequest;
import com.simplicode.portfolio.dto.response.PageNumberPaginationResponse;
import com.simplicode.portfolio.dto.response.UserResponse;
import com.simplicode.portfolio.exception.NotFoundException;
import com.simplicode.portfolio.model.Page;
import com.simplicode.portfolio.model.User;
import com.simplicode.portfolio.repository.UserRepository;
import com.simplicode.portfolio.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    public Integer countAll() {
        return userRepository.countAll();
    }

    public PageNumberPaginationResponse<UserResponse> findAll(Page page) {
        List<UserResponse> results = userRepository.findAll(page).stream()
           .map(user -> modelMapper.map(user, UserResponse.class)
              .setFullName(user.getFullName())
           ).toList();

        return new PageNumberPaginationResponse<UserResponse>()
           .setCount(results.size())
           .setNext(page.getNext())
           .setPrevious(page.getPrevious())
           .setResults(results);
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

    public void deleteById(Long id) {
        UserResponse userResponse = findById(id);
        userRepository.deleteById(userResponse.getId());
    }

}
