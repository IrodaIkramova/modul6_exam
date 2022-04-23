package com.example.modul6_exam.entity.enums;

import lombok.Getter;

@Getter
public enum Category {
    HIGHER("HIGHER"),
    LOWER("LOWER"),
    AVERAGE("AVERAGE");
    private String description;
    Category(String description) {
        this.description = description;
    }
}
