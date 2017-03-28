package com.dwalldorf.trackmytime.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import com.dwalldorf.trackmytime.BaseTest;
import com.dwalldorf.trackmytime.model.WorkEntry;
import com.dwalldorf.trackmytime.service.CustomerService;
import com.dwalldorf.trackmytime.service.ProjectService;
import com.dwalldorf.trackmytime.service.UserService;
import com.dwalldorf.trackmytime.service.WorkEntryService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

public class WorkControllerTest extends BaseTest {

    @Mock
    private UserService mockUserService;

    @Mock
    private WorkEntryService mockWorkEntryService;

    @Mock
    private CustomerService mockCustomerService;

    @Mock
    private ProjectService mockProjectService;

    private WorkController workController;

    @Override
    protected void setUp() {
        this.workController = new WorkController(
                mockUserService,
                mockWorkEntryService,
                mockCustomerService,
                mockProjectService
        );
    }

    @Test
    public void testSave_NewEntry() {
        final String mockCurrentUserId = "58d7f5895ff8d846183ebbcb";
        when(mockUserService.getCurrentUserId()).thenReturn(mockCurrentUserId);
        WorkEntry workEntry = createWorkEntry();

        workController.save(workEntry);

        ArgumentCaptor<WorkEntry> workEntryCaptor = ArgumentCaptor.forClass(WorkEntry.class);
        verify(mockWorkEntryService).save(workEntryCaptor.capture());

        WorkEntry capturedWorkEntry = workEntryCaptor.getValue();
        assertNotNull(capturedWorkEntry.getUserId());
        assertEquals(mockCurrentUserId, capturedWorkEntry.getUserId());
        assertEquals(workEntry.getCustomerId(), capturedWorkEntry.getCustomerId());
        assertEquals(workEntry.getProjectId(), capturedWorkEntry.getProjectId());
        assertEquals(workEntry.getComment(), capturedWorkEntry.getComment());
    }

    @Test
    public void testSave_ExistingEntry() {
        final String mockCurrentUserId = "58d7f5895ff8d846183ebbcb";
        when(mockUserService.getCurrentUserId()).thenReturn(mockCurrentUserId);
        WorkEntry workEntry = createWorkEntry();
        workEntry.setId("2c11acf45ff8d8461834f299")
                 .setUserId(mockCurrentUserId);

        when(mockWorkEntryService.findById(eq(workEntry.getId()))).thenReturn(workEntry);

        workController.save(workEntry);

        ArgumentCaptor<WorkEntry> workEntryCaptor = ArgumentCaptor.forClass(WorkEntry.class);
        verify(mockWorkEntryService).save(workEntryCaptor.capture());

        WorkEntry capturedWorkEntry = workEntryCaptor.getValue();
        assertEquals(mockCurrentUserId, capturedWorkEntry.getUserId());
        assertEquals(workEntry.getCustomerId(), capturedWorkEntry.getCustomerId());
        assertEquals(workEntry.getProjectId(), capturedWorkEntry.getProjectId());
        assertEquals(workEntry.getComment(), capturedWorkEntry.getComment());
    }

    @Test
    public void testSave_VerifiesResourceOwner() {
        final String mockCurrentUserId = "58d7f5895ff8d846183ebbcb";
        final String originalUserId = "2c11acf45ff8d8461834f299";
        when(mockUserService.getCurrentUserId()).thenReturn(mockCurrentUserId);

        WorkEntry workEntry = createWorkEntry()
                .setId("2c11acf45ff8d8461834f299")
                .setUserId(originalUserId);
        when(mockWorkEntryService.findById(eq(workEntry.getId()))).thenReturn(workEntry);

        workController.save(workEntry);

        verify(mockUserService).verifyOwner(eq(workEntry));
    }

    @Test
    public void testEditPage_VerifiesResourceOwner() {
        final String id = "58d7f5925ff8d846183ebbcc";
        WorkEntry mockPersistedEntry = new WorkEntry().setId(id);
        when(mockWorkEntryService.findById(eq(id))).thenReturn(mockPersistedEntry);

        workController.editPage(id);

        verify(mockUserService).verifyOwner(eq(mockPersistedEntry));
    }

    @Test
    public void testDelete() {
        final String id = "58d7f5925ff8d846183ebbcc";
        WorkEntry mockPersistedEntry = new WorkEntry().setId(id);
        when(mockWorkEntryService.findById(eq(id))).thenReturn(mockPersistedEntry);

        workController.delete(id);

        verify(mockWorkEntryService).delete(eq(mockPersistedEntry));
    }

    @Test
    public void testDelete_VerifiesResourceOwner() {
        final String id = "58d7f5925ff8d846183ebbcc";
        WorkEntry mockPersistedEntry = new WorkEntry().setId(id);
        when(mockWorkEntryService.findById(eq(id))).thenReturn(mockPersistedEntry);

        workController.delete(id);

        verify(mockUserService).verifyOwner(eq(mockPersistedEntry));
    }

    private WorkEntry createWorkEntry() {
        return new WorkEntry()
                .setId(null)
                .setUserId(null)
                .setCustomerId("someId")
                .setProjectId("someOtherId")
                .setComment("some comment");
    }
}