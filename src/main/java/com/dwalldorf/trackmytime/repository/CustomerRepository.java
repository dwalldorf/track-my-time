package com.dwalldorf.trackmytime.repository;

import com.dwalldorf.trackmytime.model.Customer;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    List<Customer> findByUserId(String userId);
}