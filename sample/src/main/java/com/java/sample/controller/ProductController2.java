package com.java.sample.controller;

import com.java.sample.entity.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping(produces= MediaType.APPLICATION_JSON_VALUE)
//public class ProductController2() {
//
//    private final List<Product> productDB = new ArrayList<>();
//    //為java 的註解 在伺服器載入servlet時執行 且只會被伺服器執行一次
//    @PostConstruct
//    private void initDB() {
//        productDB.add(new Product("B0001", "Android Development (Java)", 380));
//        productDB.add(new Product("B0002", "Android Development (Kotlin)", 420));
//        productDB.add(new Product("B0003", "Data Structure (Java)", 250));
//        productDB.add(new Product("B0004", "Finance Management", 450));
//        productDB.add(new Product("B0005", "Human Resource Management", 330));
//    }
//
//    @GetMapping("/products/{id}")
//    public ResponseEntity<Product> getProduct(@PathVariable("id") String id ){
//
//        Optional<Product> productOp=productDB.stream().filter(p-> p.getId().equals(id)).findFirst();
//
//        if(productOp.isPresent()){
//            Product product =productOp.get();
//            return ResponseEntity.ok().body(product);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//
//}
