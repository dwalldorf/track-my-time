package com.dwalldorf.trackmytime.repository;

import com.dwalldorf.trackmytime.model.WorkEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkEntryRepository extends MongoRepository<WorkEntry, String> {
}