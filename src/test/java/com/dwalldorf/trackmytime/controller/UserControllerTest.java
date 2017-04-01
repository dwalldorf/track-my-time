package com.dwalldorf.trackmytime.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.dwalldorf.trackmytime.BaseTest;
import com.dwalldorf.trackmytime.forms.user.RegisterForm;
import com.dwalldorf.trackmytime.service.CustomerService;
import com.dwalldorf.trackmytime.service.UserService;
import java.util.Map;
import org.junit.Test;
import org.mockito.Mock;

public class UserControllerTest extends BaseTest {

    @Mock
    private UserService mockUserService;

    @Mock
    private CustomerService mockCustomerService;

    private UserController userController;

    @Override
    protected void setUp() {
        this.userController = new UserController(mockUserService, mockCustomerService);
    }

    @Test
    public void testRegisterPage_ViewName() {
        final String expectedViewName = "/user/register";
        final String actualViewName = userController.registerPage(new RegisterForm());

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    public void testRegister() {
        fail("write test");
    }

    @Test
    public void testEditPage_Model() {
        final String expectedUserModelKey = "user";
        final Map<String, Object> model = userController.editPage().getModel();

        assertTrue(model.containsKey(expectedUserModelKey));
    }

    @Test
    public void testEditPage_ViewName() {
        final String expectedView = "/user/edit";
        final String viewName = userController.editPage().getViewName();

        assertEquals(expectedView, viewName);
    }

    @Test
    public void testUpdate() {
        fail("write test");
    }
}