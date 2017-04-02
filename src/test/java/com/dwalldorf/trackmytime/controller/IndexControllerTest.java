package com.dwalldorf.trackmytime.controller;

import static org.junit.Assert.assertEquals;

import com.dwalldorf.trackmytime.BaseTest;
import com.dwalldorf.trackmytime.util.RouteUtil;
import org.junit.Test;

public class IndexControllerTest extends BaseTest {

    private IndexController indexController;

    @Override
    protected void setUp() {
        this.indexController = new IndexController();
    }

    @Test
    public void testIndex_Redirect() throws Exception {
        final String expectedRedirect = RouteUtil.redirectString("/work/list");
        final String actualRedirect = indexController.index();

        assertEquals(expectedRedirect, actualRedirect);
    }
}