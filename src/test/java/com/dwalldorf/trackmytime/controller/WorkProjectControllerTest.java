package com.dwalldorf.trackmytime.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import com.dwalldorf.trackmytime.BaseTest;
import com.dwalldorf.trackmytime.model.Project;
import com.dwalldorf.trackmytime.service.ProjectService;
import com.dwalldorf.trackmytime.service.UserService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

public class WorkProjectControllerTest extends BaseTest {

    @Mock
    private ProjectService mockProjectService;

    @Mock
    private UserService mockUserService;

    private WorkProjectController projectController;

    @Override
    protected void setUp() {
        this.projectController = new WorkProjectController(mockProjectService, mockUserService);
    }

    @Test
    public void testEditPage_VerifiesResourceOwner() {
        final String id = "58d7f5925ff8d846183ebbcc";
        Project mockPersistedProject = new Project().setId(id);
        when(mockProjectService.findById(eq(id))).thenReturn(mockPersistedProject);

        projectController.editPage(id);

        verify(mockUserService).verifyOwner(eq(mockPersistedProject));
    }

    @Test
    public void testSave_NewProject() {
        final String mockCurrentUserId = "58d7f5895ff8d846183ebbcb";
        when(mockUserService.getCurrentUserId()).thenReturn(mockCurrentUserId);
        Project project = new Project();

        projectController.save(project);

        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
        verify(mockProjectService).save(projectCaptor.capture());

        Project capturedProject = projectCaptor.getValue();
        assertNotNull(capturedProject.getUserId());
        assertEquals(mockCurrentUserId, capturedProject.getUserId());
        assertEquals(project.getName(), capturedProject.getName());
    }

    @Test
    public void testSave_UpdateProject() {
        final String mockCurrentUserId = "58d7f5895ff8d846183ebbcb";
        when(mockUserService.getCurrentUserId()).thenReturn(mockCurrentUserId);
        Project project = new Project();
        project.setId("2c11acf45ff8d8461834f299")
                .setUserId(mockCurrentUserId);

        when(mockProjectService.findById(eq(project.getId()))).thenReturn(project);

        projectController.save(project);

        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
        verify(mockProjectService).save(projectCaptor.capture());

        Project capturedProject = projectCaptor.getValue();
        assertEquals(mockCurrentUserId, capturedProject.getUserId());
        assertEquals(project.getName(), capturedProject.getName());
    }

    @Test
    public void testSave_VerifiesResourceOwner() {
        final String mockCurrentUserId = "58d7f5895ff8d846183ebbcb";
        final String originalUserId = "2c11acf45ff8d8461834f299";
        when(mockUserService.getCurrentUserId()).thenReturn(mockCurrentUserId);

        Project project = new Project()
                .setId("2c11acf45ff8d8461834f299")
                .setUserId(originalUserId);
        when(mockProjectService.findById(eq(project.getId()))).thenReturn(project);

        projectController.save(project);

        verify(mockUserService).verifyOwner(eq(project));
    }

    @Test
    public void testDelete_VerifiesResourceOwner() {
        final String id = "58d7f5925ff8d846183ebbcc";
        Project mockPersistedProject = new Project().setId(id);
        when(mockProjectService.findById(eq(id))).thenReturn(mockPersistedProject);

        projectController.delete(id);

        verify(mockUserService).verifyOwner(eq(mockPersistedProject));
    }

    @Test
    public void testDelete() throws Exception {
        final String id = "58d7f5925ff8d846183ebbcc";
        Project mockPersistedProject = new Project().setId(id);
        when(mockProjectService.findById(eq(id))).thenReturn(mockPersistedProject);

        projectController.delete(id);

        verify(mockProjectService).delete(eq(mockPersistedProject));
    }
}