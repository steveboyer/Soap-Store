/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stephenboyer.soapstore;

//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
import com.stephenboyer.soapstore.domain.Catalog;
import com.stephenboyer.soapstore.domain.Product;
import com.stephenboyer.soapstore.util.StatusTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

@Controller
@SpringBootApplication
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class.getSimpleName());


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void execute(){
        logger.info("Startup Service started");

        Catalog catalog = CatalogFactory.getCatalog();

        List<Product> productList =  catalog.getProducts();
        logger.info("# of Products: " +  productList.size());


        logger.info("Started at " + Calendar.getInstance().getTime());

        Thread t = new StatusTimer();
        t.start();
    }
}
