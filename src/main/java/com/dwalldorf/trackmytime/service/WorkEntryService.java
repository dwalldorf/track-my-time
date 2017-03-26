package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.model.Project;
import com.dwalldorf.trackmytime.model.WorkEntry;
import com.dwalldorf.trackmytime.repository.CustomerRepository;
import com.dwalldorf.trackmytime.repository.ProjectRepository;
import com.dwalldorf.trackmytime.repository.WorkEntryRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class WorkEntryService {

    private final WorkEntryRepository workEntryRepository;

    private final CustomerRepository customerRepository;

    private final ProjectRepository projectRepository;

    @Inject
    public WorkEntryService(WorkEntryRepository workEntryRepository, CustomerRepository customerRepository, ProjectRepository projectRepository) {
        this.workEntryRepository = workEntryRepository;
        this.customerRepository = customerRepository;
        this.projectRepository = projectRepository;
    }

    public WorkEntry save(WorkEntry workEntry) {
        return workEntryRepository.save(workEntry);
    }

    public List<WorkEntry> findAllByUser(String userId) {
        return workEntryRepository.findAllByUserId(userId);
    }

    public List<Project> findAllProjectsByUser(String userId) {
        return projectRepository.findByUserId_OrderByNameAsc(userId);
    }
}