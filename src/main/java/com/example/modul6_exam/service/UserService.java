package com.example.modul6_exam.service;
import com.example.modul6_exam.dto.UserDTO;
import com.example.modul6_exam.entity.ApiResponse;
import com.example.modul6_exam.entity.Role;
import com.example.modul6_exam.entity.User;
import com.example.modul6_exam.entity.enums.RoleEnum;
import com.example.modul6_exam.repository.RoleRepository;
import com.example.modul6_exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public ApiResponse add(UserDTO dto) {
        String role = dto.getRole();
        RoleEnum roleEnum = RoleEnum.valueOf(role);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        for (Role role1 : principal.getRoleList()) {
            if (role1.getRoleName().equals(RoleEnum.ROlE_ADMIN)) {
                if (roleEnum.equals(RoleEnum.ROLE_USER2) || roleEnum.equals(RoleEnum.ROLE_USER )|| roleEnum.equals(RoleEnum.ROLE_USER1)) {
                    User user = new User();
                    user.setEmail(dto.getEmail());
                    user.setUsername(dto.getUsername());
                    user.setPassword(passwordEncoder.encode("1111"));

                    Optional<Role> byRoleName = roleRepository.findByRoleName(roleEnum);
                    user.setRoleList((List<Role>) new LinkedHashSet(Collections.singleton(byRoleName.get())));
                    User save = userRepository.save(user);
                }
            } else if (role1.getRoleName().equals(RoleEnum.ROLE_USER2)) {
                if (role.equals(RoleEnum.ROLE_USER.name())) {
                    User user = new User();
                    user.setEmail(dto.getEmail());
                    user.setUsername(dto.getUsername());
                    user.setPassword(passwordEncoder.encode("1111"));

                    Optional<Role> byRoleName = roleRepository.findByRoleName(roleEnum);
                    user.setRoleList((List<Role>) new LinkedHashSet(Collections.singleton(byRoleName.get())));
                    userRepository.save(user);
                }
            }
        }
        return ApiResponse.builder().message("Added!").success(true).build();
    }


    public ApiResponse edit(Integer id, UserDTO dto) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) return new ApiResponse("User not found", false);

        User user = optionalUser.get();

        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoleList((List<Role>) new LinkedHashSet<Role>(Integer.parseInt(dto.getRole())));

        userRepository.save(user);

        return new ApiResponse("Edited", true);
    }
    }

