package com.example.modul6_exam.component;

import com.example.modul6_exam.entity.Role;
import com.example.modul6_exam.entity.User;
import com.example.modul6_exam.entity.enums.PermissionEnum;
import com.example.modul6_exam.entity.enums.RoleEnum;
import com.example.modul6_exam.repository.RoleRepository;
import com.example.modul6_exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final RoleRepository roleRepository;
    @Value("${spring.sql.init.mode}")
    String mode;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            PermissionEnum[] values = PermissionEnum.values();

            List<Role> roleList = new ArrayList<>();
            Role userRole = roleRepository.save(new Role(RoleEnum.ROLE_USER, new ArrayList<>(Arrays.asList(
                    PermissionEnum.READ_ALL_PRODUCT,
                    PermissionEnum.READ_ONE_PRODUCT
            ))));
            Role adminRole = roleRepository.save(new Role(RoleEnum.ROlE_ADMIN, Arrays.asList(values)));
            Role user1 = roleRepository.save(new Role(RoleEnum.ROLE_USER1, Arrays.asList(
                    PermissionEnum.READ_ALL_PRODUCT,
                    PermissionEnum.READ_ONE_PRODUCT,
                    PermissionEnum.ADD_PRODUCT
            )));
            Role user2 = roleRepository.save(new Role(RoleEnum.ROLE_USER2, Arrays.asList(
                    PermissionEnum.READ_ALL_PRODUCT,
                    PermissionEnum.READ_ONE_PRODUCT,
                    PermissionEnum.ADD_PRODUCT,
                    PermissionEnum.CRUD_PRODUCT,
                    PermissionEnum.DELETE_PRODUCT,
                    PermissionEnum.EDIT_PRODUCT
            )));
            roleList.add(userRole);
            roleList.add(adminRole);
            roleList.add(user1);
            roleList.add(user2);

            userRepository.save(new User("Iroda", passwordEncoder.encode("123"), roleList.get(1)));
            userRepository.save(new User("Murod", passwordEncoder.encode("123"), roleList.get(0)));
            userRepository.save(new User("Jafarbek", passwordEncoder.encode("123"), roleList.get(2)));
            userRepository.save(new User("Asal", passwordEncoder.encode("123"), roleList.get(3)));

        }
    }
}
