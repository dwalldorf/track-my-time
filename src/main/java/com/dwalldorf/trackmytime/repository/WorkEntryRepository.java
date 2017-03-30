package com.dwalldorf.trackmytime.repository;

import com.dwalldorf.trackmytime.model.WorkEntry;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkEntryRepository extends MongoRepository<WorkEntry, String> {

    List<WorkEntry> findAllByUserId_OrderByStartDesc(String userId);
}