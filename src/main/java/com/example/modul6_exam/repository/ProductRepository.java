package com.example.modul6_exam.repository;

import com.example.modul6_exam.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByActiveTrue();
    List<Product> findAllById(Integer id);
}