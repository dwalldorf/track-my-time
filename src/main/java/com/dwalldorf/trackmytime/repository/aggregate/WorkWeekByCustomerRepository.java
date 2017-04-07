package com.dwalldorf.trackmytime.repository.aggregate;

import com.dwalldorf.trackmytime.model.aggregate.WorkWeekByCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkWeekByCustomerRepository extends MongoRepository<WorkWeekByCustomer, String> {
}