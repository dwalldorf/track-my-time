package com.dwalldorf.trackmytime.util;

import static org.junit.Assert.assertEquals;

import com.dwalldorf.trackmytime.BaseTest;
import org.junit.Test;

public class RouteUtilTest extends BaseTest {

    private RouteUtil routeUtil;

    @Override
    protected void setUp() {
        this.routeUtil = new RouteUtil();
    }

    @Test
    public void testRedirectString() {
        final String input = "/home";
        final String expectedOutput = "redirect:" + input;

        String output = RouteUtil.redirectString(input);

        assertEquals(expectedOutput, output);
    }
}