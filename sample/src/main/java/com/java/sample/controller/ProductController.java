package com.java.sample.controller;


import com.java.sample.entity.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces= MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") String id){
        Product product =new Product();
        product.setId(id);
        product.setName("Romantic Story");
        product.setPrice(200);
        return product;
    }
}
