package com.java.sample.mysql.controller;


import com.java.sample.mysql.entity.Customer;
import com.java.sample.mysql.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CustomerController {

    private CustomerRepository customerRepository;
    private DataSource dataSource;

    static final int BATCH_SIZE = 10000;
    @Autowired
    public  void setCustomerRepository(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
    }

    @GetMapping("/jpaCase1")
    public ResponseEntity<String> jpaCase1(){
       List<Customer>customers= prepareCustomerList();
       long start=System.currentTimeMillis();
       customerRepository.saveAll((Iterable<Customer>)customers);
       long end = System.currentTimeMillis();
       String result=String.format("Total time: %d 毫秒",(end -start));
       log.debug(result);
       return ResponseEntity.ok(result);
    }

    @GetMapping("/jdbcCase")
    public ResponseEntity<String> jdbcCase(){
        Connection connection = null;
        PreparedStatement pstmt = null;
        long end = 0L;
        String result = "";
        String sql="INSERT INTO CUSTOMER (first_name,last_name) VALUES (?,?)";
        List<Customer>customers= prepareCustomerList();
        long start=System.currentTimeMillis();
        try{
            connection=dataSource.getConnection();
            connection.setAutoCommit(false);
            pstmt=connection.prepareStatement(sql);

            for(Customer customer : customers){
                pstmt.setString(1, customer.getFirstName());
                pstmt.setString(2, customer.getLastName());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            end = System.currentTimeMillis();
            result = String.format("Total time: %d 毫秒", (end - start));

            connection.commit();
        }catch (SQLException e){
            try{
                connection.rollback();
            }catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
        }finally{
            if(pstmt !=null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    log.debug("preparedStatement.close() fail.", e);
                }

            }
            if(connection !=null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                }catch(SQLException e){
                    log.debug("connection.close() fail.", e);
                }
            }
        }
        log.debug(result);
        return ResponseEntity.ok(result);


    }


    private List<Customer> prepareCustomerList(){
        List<Customer> customers=new ArrayList<>();
        for(int i=0; i<BATCH_SIZE ;i++ ){
            customers.add(new Customer("Peter","lastName"));
        }
        return customers;
    }



}
