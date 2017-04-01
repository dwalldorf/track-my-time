package com.dwalldorf.trackmytime.controller;

import static org.junit.Assert.assertEquals;

import com.dwalldorf.trackmytime.BaseTest;
import org.junit.Test;

public class IndexControllerTest extends BaseTest {

    private IndexController indexController;

    @Override
    protected void setUp() {
        this.indexController = new IndexController();
    }

    @Test
    public void testIndex_ReturnsIndex() throws Exception {
        final String expectedView = "index";
        final String actualView = indexController.index();

        assertEquals(expectedView, actualView);
    }

    @Test
    public void testHome_ReturnsSameAsIndex() throws Exception {
        final String expectedView = indexController.index();
        final String actualView = indexController.home();

        assertEquals(expectedView, actualView);
    }
}