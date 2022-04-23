package com.example.modul6_exam.entity.enums;

import lombok.Data;


public enum RoleEnum {
    //static
    ROLE_USER("user"), //tengkuchli == RolEnum enum = new RoleEnum("Role USER")
    ROlE_ADMIN("admin"),
    ROLE_USER1("user1"),
    ROLE_USER2("user2");

    private String description;
    RoleEnum(String description) {
        this.description = description;
    }
}
