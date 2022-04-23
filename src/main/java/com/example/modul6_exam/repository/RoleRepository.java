package com.example.modul6_exam.repository;


import com.example.modul6_exam.entity.Role;
import com.example.modul6_exam.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleEnum name);

    List<Role> findAllById(String role);
}
