package com.example.modul6_exam.controller;

import com.example.modul6_exam.dto.ProductDto;
import com.example.modul6_exam.entity.ApiResponse;
import com.example.modul6_exam.entity.Product;
import com.example.modul6_exam.repository.ProductRepository;
import com.example.modul6_exam.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/data/product")
@RequiredArgsConstructor
public class ProductController {
    final ProductRepository productRepository;
    final ProductService productService;

    @PreAuthorize("hasAnyAuthority('PRODUCT_CRUD', 'PRODUCT_READ')")
    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(productRepository.findAll());
    }

    @PreAuthorize("hasAnyAuthority('PRODUCT_CRUD', 'PRODUCT_READ')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Optional<Product> optionalCompany = productRepository.findById(id);
        if(optionalCompany.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalCompany.get());
    }

    @PreAuthorize("hasAnyAuthority('PRODUCT_CRUD', 'PRODUCT_ADD')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody ProductDto productDto){
        ApiResponse apiResponse=productService.add(productDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('PRODUCT_CRUD', 'PRODUCT_EDIT')")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ProductDto productDto){
        ApiResponse apiResponse=productService.edit(id, productDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('PRODUCT_CRUD', 'PRODUCT_DELETE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse=productService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:404).body(apiResponse);
    }
}
