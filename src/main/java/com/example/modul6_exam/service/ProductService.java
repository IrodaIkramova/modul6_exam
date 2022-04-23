package com.example.modul6_exam.service;
import com.example.modul6_exam.dto.ProductDto;
import com.example.modul6_exam.entity.ApiResponse;
import com.example.modul6_exam.entity.Product;
import com.example.modul6_exam.entity.User;
import com.example.modul6_exam.repository.ProductRepository;
import com.example.modul6_exam.repository.RoleRepository;
import com.example.modul6_exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    final RoleRepository roleRepository;
    final ProductRepository productRepository;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;


    public ApiResponse add(ProductDto productDto) {

         Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        productRepository.save(product);
        return ApiResponse.builder().success(true).message("Company created!").build();
    }

    public ApiResponse edit(Integer id, ProductDto productDto) {
        Optional<Product> optionalCompany = productRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return ApiResponse.builder().message("Company not found").build();
        }
        Product product = optionalCompany.get();

        if (productDto.getName() != null) {
            product.setName(productDto.getName());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
        }
        productRepository.save(product);
        return ApiResponse.builder().message("Company edited!").success(true).build();
    }

    public ApiResponse delete(Integer id) {
        if (!productRepository.existsById(id)) {
            return ApiResponse.builder().message("Company not found").build();
        }
        productRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Company deleted!").build();
    }
}

