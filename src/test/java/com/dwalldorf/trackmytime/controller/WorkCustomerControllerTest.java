package com.dwalldorf.trackmytime.controller;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import com.dwalldorf.trackmytime.BaseTest;
import com.dwalldorf.trackmytime.model.Customer;
import com.dwalldorf.trackmytime.service.CustomerService;
import com.dwalldorf.trackmytime.service.UserService;
import org.junit.Test;
import org.mockito.Mock;

public class WorkCustomerControllerTest extends BaseTest {

    @Mock
    private CustomerService mockCustomerService;

    @Mock
    private UserService mockUserService;

    private WorkCustomerController customerController;

    @Override
    protected void setUp() {
        this.customerController = new WorkCustomerController(mockCustomerService, mockUserService);
    }

    @Test
    public void editPage() {
        final String id = "58d7f5925ff8d846183ebbcc";
        Customer mockPersistedCustomer = new Customer().setId(id);
        when(mockCustomerService.findById(eq(id))).thenReturn(mockPersistedCustomer);


        customerController.editPage(id);

        verify(mockUserService).verifyOwner(eq(mockPersistedCustomer));
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
        final String id = "58d7f5925ff8d846183ebbcc";
        Customer mockPersistedCustomer = new Customer().setId(id);
        when(mockCustomerService.findById(eq(id))).thenReturn(mockPersistedCustomer);


        customerController.delete(id);

        verify(mockUserService).verifyOwner(eq(mockPersistedCustomer));
    }
}