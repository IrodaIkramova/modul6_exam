package com.example.modul6_exam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private Object object;
    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }


}
