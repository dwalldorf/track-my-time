package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.repository.WorkEntryRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class WorkEntryService {

    private final WorkEntryRepository workEntryRepository;

    @Inject
    public WorkEntryService(WorkEntryRepository workEntryRepository) {
        this.workEntryRepository = workEntryRepository;
    }
}