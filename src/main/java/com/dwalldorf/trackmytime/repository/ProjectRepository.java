package com.dwalldorf.trackmytime.repository;

import com.dwalldorf.trackmytime.model.Project;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {

    List<Project> findByUserId(String userId);
}