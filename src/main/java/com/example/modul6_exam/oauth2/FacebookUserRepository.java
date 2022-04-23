package com.example.modul6_exam.oauth2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "oauth2")
public interface FacebookUserRepository extends JpaRepository<FacebookUser, Integer> {

}
