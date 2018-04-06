package com.stephenboyer.soapstore.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class.getSimpleName());
    @Test
    void robots() {
    }

    @Test
    void index() {
        log.info("start");
        IndexController ic = new IndexController();

        ModelAndView mav = ic.index(null);


    }
}