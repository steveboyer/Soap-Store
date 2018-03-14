package com.stephenboyer.soapstore.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    @Test
    void robots() {
    }

    @Test
    void index() {
        IndexController ic = new IndexController();

        ModelAndView mav = ic.index(null);


    }
}