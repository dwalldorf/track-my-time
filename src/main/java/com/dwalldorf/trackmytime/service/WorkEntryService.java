package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.model.WorkEntry;
import com.dwalldorf.trackmytime.repository.WorkEntryRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class WorkEntryService {

    private final WorkEntryRepository workEntryRepository;

    @Inject
    public WorkEntryService(WorkEntryRepository workEntryRepository) {
        this.workEntryRepository = workEntryRepository;
    }

    public WorkEntry save(WorkEntry workEntry) {
        return workEntryRepository.save(workEntry);
    }

    public List<WorkEntry> findAllByUser(String userId) {
        return workEntryRepository.findAllByUserId_OrderByStartDesc(userId);
    }

    public WorkEntry findById(String id) {
        return workEntryRepository.findOne(id);
    }

    public void delete(WorkEntry workEntry) {
        workEntryRepository.delete(workEntry);
    }
}