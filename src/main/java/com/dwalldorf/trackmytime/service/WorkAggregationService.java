package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.repository.WorkEntryRepository;
import com.dwalldorf.trackmytime.repository.aggregate.WorkWeekByCustomerRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class WorkAggregationService {

    private final WorkWeekByCustomerRepository workWeekByCustomerRepository;

    private final WorkEntryRepository workEntryRepository;

    @Inject
    public WorkAggregationService(WorkWeekByCustomerRepository workWeekByCustomerRepository, WorkEntryRepository workEntryRepository) {
        this.workWeekByCustomerRepository = workWeekByCustomerRepository;
        this.workEntryRepository = workEntryRepository;
    }
}