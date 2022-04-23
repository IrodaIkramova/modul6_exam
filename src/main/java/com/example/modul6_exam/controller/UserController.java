package com.example.modul6_exam.controller;

import com.example.modul6_exam.dto.UserDTO;
import com.example.modul6_exam.entity.ApiResponse;
import com.example.modul6_exam.entity.User;
import com.example.modul6_exam.repository.UserRepository;
import com.example.modul6_exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    @PreAuthorize("hasAnyAuthority('USER_CRUD', 'USER_READ')")
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }
    @PreAuthorize("hasAnyAuthority('USER_CRUD', 'USER_READ','USER_ONE_EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        Optional<User> byId = userRepository.findById(id);
        return ResponseEntity.status(byId.isEmpty() ?
                HttpStatus.NOT_FOUND : HttpStatus.OK).body(byId.orElse(new User()));
    }
    @PreAuthorize("hasAnyAuthority('USER_CRUD', 'USER_DELETE')")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.ok().body("User not Found");
        }

        userRepository.delete(user.get());
        return ResponseEntity.ok().body("Deleted");
    }
    @PreAuthorize("hasAnyAuthority('USER_CRUD', 'USER_ADD')")
    @PostMapping
    public ResponseEntity add(@RequestBody UserDTO dto) {
        ApiResponse add = userService.add(dto);
        return ResponseEntity.ok().body(add);
    }
    @PreAuthorize("hasAnyAuthority('USER_CRUD', 'USER_EDIT')")
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id, @RequestBody UserDTO dto) {
        ApiResponse response = userService.edit(id, dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

}
