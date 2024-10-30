package com.simplicode.portfolio.controller;

import com.simplicode.portfolio.dto.request.UserCreateRequest;
import com.simplicode.portfolio.dto.request.UserUpdateRequest;
import com.simplicode.portfolio.dto.response.GlobalResponse;
import com.simplicode.portfolio.dto.response.UserResponse;
import com.simplicode.portfolio.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<GlobalResponse> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
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
