package com.example.modul6_exam.controller;

import com.example.modul6_exam.config.EmailConfig;
import com.example.modul6_exam.dto.LoginDTO;
import com.example.modul6_exam.security.JwtProvider;
import com.example.modul6_exam.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    final JwtProvider jwtProvider;
    final AuthService authService;
    final EmailConfig emailConfig;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        UserDetails userDetails = authService.loadUserByUsername(loginDTO.getName());
        String token = jwtProvider.generateToken(loginDTO.getName());
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText("Salom do'st");
//        message.setSubject("Title bu akasi!");
//        message.setSentDate(new Date());
//        message.setTo("jafarbek1997@gmail.com");
//        message.setFrom("pdp.uz@gmail.com");
//
//        JavaMailSender mailSender = emailConfig.send();
//        mailSender.send(message);

        return ResponseEntity.ok().body(token);
    }
}
