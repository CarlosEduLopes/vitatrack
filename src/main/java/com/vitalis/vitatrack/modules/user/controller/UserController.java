package com.vitalis.vitatrack.modules.user.controller;

import com.vitalis.vitatrack.modules.user.dto.CreateUserRequest;
import com.vitalis.vitatrack.modules.user.dto.UpdateUserRequest;
import com.vitalis.vitatrack.modules.user.dto.UserResponse;
import com.vitalis.vitatrack.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public UserResponse create(@RequestBody @Valid CreateUserRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserRequest request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}