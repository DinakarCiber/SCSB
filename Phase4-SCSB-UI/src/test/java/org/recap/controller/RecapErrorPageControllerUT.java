package org.recap.controller;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 22/2/17.
 */
public class RecapErrorPageControllerUT extends BaseTestCase{

    @Autowired
    RecapErrorPageController recapErrorPageController;

    @Test
    public void testErrorPage(){
        String response = recapErrorPageController.recapErrorPage();
        assertNotNull(response);
        assertEquals(response,"error");
    }



}