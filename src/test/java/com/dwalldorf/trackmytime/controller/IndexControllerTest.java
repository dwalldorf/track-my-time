package com.dwalldorf.trackmytime.controller;

import static org.junit.Assert.assertEquals;

import com.dwalldorf.trackmytime.BaseTest;
import org.junit.Test;

public class IndexControllerTest extends BaseTest {

    private IndexController indexController;

    private static final String EXPECTED_VIEW = "index";

    @Override
    protected void setUp() {
        this.indexController = new IndexController();
    }

    @Test
    public void testIndex_ReturnsIndex() throws Exception {
        final String actualView = indexController.index();
        assertEquals(EXPECTED_VIEW,actualView);
    }

    @Test
    public void testHome_ReturnsIndex() throws Exception {
        final String actualView = indexController.home();
        assertEquals(EXPECTED_VIEW,actualView);
    }
}