package com.java.sample.mysql.repository;


import com.java.sample.mysql.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
