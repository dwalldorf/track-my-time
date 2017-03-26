package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.model.Project;
import com.dwalldorf.trackmytime.repository.ProjectRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Inject
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllByUser(String userId) {
        return projectRepository.findByUserId(userId);
    }
}