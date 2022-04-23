package com.example.modul6_exam.controller;

import com.example.modul6_exam.entity.enums.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CATEGORYController {
    @GetMapping
    public HttpEntity<?> getAll(){
        Category[] sinfEnum=Category.class.getEnumConstants();
        return ResponseEntity.ok().body(sinfEnum);
    }
}
