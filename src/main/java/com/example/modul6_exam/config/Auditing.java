package com.example.modul6_exam.config;

import com.example.modul6_exam.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Auditing implements AuditorAware<Long> {
    @Override
    public Optional getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals("" + authentication.getPrincipal()))) {
            return Optional.of(((User) authentication.getPrincipal()).getId());
        } else {
            return Optional.empty();
        }
    }
}
