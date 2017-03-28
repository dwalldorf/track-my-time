package com.dwalldorf.trackmytime.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import com.dwalldorf.trackmytime.BaseTest;
import com.dwalldorf.trackmytime.model.Customer;
import com.dwalldorf.trackmytime.service.CustomerService;
import com.dwalldorf.trackmytime.service.UserService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
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
    public void testEditPage_VerifiesResourceOwner() {
        final String id = "58d7f5925ff8d846183ebbcc";
        Customer mockPersistedCustomer = new Customer().setId(id);
        when(mockCustomerService.findById(eq(id))).thenReturn(mockPersistedCustomer);

        customerController.editPage(id);

        verify(mockUserService).verifyOwner(eq(mockPersistedCustomer));
    }

    @Test
    public void testSave_NewCustomer() {
        final String mockCurrentUserId = "58d7f5895ff8d846183ebbcb";
        when(mockUserService.getCurrentUserId()).thenReturn(mockCurrentUserId);
        Customer customer = new Customer();

        customerController.save(customer);

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(mockCustomerService).save(customerCaptor.capture());

        Customer capturedCustomer = customerCaptor.getValue();
        assertNotNull(capturedCustomer.getUserId());
        assertEquals(mockCurrentUserId, capturedCustomer.getUserId());
        assertEquals(customer.getName(), capturedCustomer.getName());
    }

    @Test
    public void testSave_UpdateCustomer() {
        final String mockCurrentUserId = "58d7f5895ff8d846183ebbcb";
        when(mockUserService.getCurrentUserId()).thenReturn(mockCurrentUserId);
        Customer customer = new Customer();
        customer.setId("2c11acf45ff8d8461834f299")
                .setUserId(mockCurrentUserId);

        when(mockCustomerService.findById(eq(customer.getId()))).thenReturn(customer);

        customerController.save(customer);

        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(mockCustomerService).save(customerCaptor.capture());

        Customer capturedCustomer = customerCaptor.getValue();
        assertEquals(mockCurrentUserId, capturedCustomer.getUserId());
        assertEquals(customer.getName(), capturedCustomer.getName());
    }

    @Test
    public void testSave_VerifiesResourceOwner() {
        final String mockCurrentUserId = "58d7f5895ff8d846183ebbcb";
        final String originalUserId = "2c11acf45ff8d8461834f299";
        when(mockUserService.getCurrentUserId()).thenReturn(mockCurrentUserId);

        Customer customer = new Customer()
                .setId("2c11acf45ff8d8461834f299")
                .setUserId(originalUserId);
        when(mockCustomerService.findById(eq(customer.getId()))).thenReturn(customer);

        customerController.save(customer);

        verify(mockUserService).verifyOwner(eq(customer));
    }

    @Test
    public void testDelete_VerifiesResourceOwner() {
        final String id = "58d7f5925ff8d846183ebbcc";
        Customer mockPersistedCustomer = new Customer().setId(id);
        when(mockCustomerService.findById(eq(id))).thenReturn(mockPersistedCustomer);


        customerController.delete(id);

        verify(mockUserService).verifyOwner(eq(mockPersistedCustomer));
    }

    @Test
    public void testDelete() throws Exception {
        final String id = "58d7f5925ff8d846183ebbcc";
        Customer mockPersistedCustomer = new Customer().setId(id);
        when(mockCustomerService.findById(eq(id))).thenReturn(mockPersistedCustomer);

        customerController.delete(id);

        verify(mockCustomerService).delete(eq(mockPersistedCustomer));
    }
}