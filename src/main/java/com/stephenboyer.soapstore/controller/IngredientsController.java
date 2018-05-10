package com.stephenboyer.soapstore.controller;

import com.stephenboyer.soapstore.CatalogFactory;
import com.stephenboyer.soapstore.domain.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class IngredientsController  {

    private static final Logger logger = LoggerFactory.getLogger(com.stephenboyer.soapstore.controller.IngredientsController.class.getSimpleName());

    // Homepage
    @RequestMapping(value = "/ingredient/{name}", method = RequestMethod.GET)
    public String index(Model model) {
        // Return template name and ingredient data

        return "ingredient";
    }

}
