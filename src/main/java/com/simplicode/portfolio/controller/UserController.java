package com.simplicode.portfolio.controller;

import com.simplicode.portfolio.dto.request.UserCreateRequest;
import com.simplicode.portfolio.dto.request.UserUpdateRequest;
import com.simplicode.portfolio.dto.response.PageNumberPaginationResponse;
import com.simplicode.portfolio.dto.response.UserResponse;
import com.simplicode.portfolio.model.Page;
import com.simplicode.portfolio.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> save(
       @Valid @RequestBody UserCreateRequest userCreateRequest
    ) {
        userService.save(userCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageNumberPaginationResponse<UserResponse>> findAll(
       @RequestParam(defaultValue = "1") @Min(1) Integer pageNumber,
       @RequestParam(defaultValue = "30") @Min(1) Integer pageSize,
       HttpServletRequest httpRequest
    ) {
        Page page = new Page()
           .setCount(userService.countAll())
           .setRequestUrl(httpRequest.getRequestURL().toString())
           .setPageNumber(pageNumber)
           .setPageSize(pageSize);

        return new ResponseEntity<>(userService.findAll(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(
       @PathVariable Long id,
       @Valid @RequestBody UserUpdateRequest userUpdateRequest
    ) {
        userService.updateById(id, userUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
