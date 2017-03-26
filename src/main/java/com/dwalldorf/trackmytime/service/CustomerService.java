package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.model.Customer;
import com.dwalldorf.trackmytime.repository.CustomerRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Inject
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAllByUser(String userId) {
        return customerRepository.findByUserId(userId);
    }
}