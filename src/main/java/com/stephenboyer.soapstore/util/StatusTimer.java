package com.stephenboyer.soapstore.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class StatusTimer extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(StatusTimer.class.getSimpleName());

    @Override
    public void run(){
        while (true) {
            logger.info("Time: " + Calendar.getInstance().getTime());
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

