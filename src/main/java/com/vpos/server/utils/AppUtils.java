package com.vpos.server.utils;

/*
 * @created 11/04/2023 - 3:26 AM
 * @project server
 * @author Rithy SKUN
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtils {

    private final static Logger log = LoggerFactory.getLogger(AppUtils.class);

    //Format DateTime
    public static String format(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    //Manual logger
    public static String logger(String level, String message) {
        switch (level) {
            case "INFO" -> {
                log.info(message);
            }
            case "ERROR" -> {
                log.error(message);
            }
            case "DEBUG" -> {
                log.debug(message);
            }
            case "TRACE" -> {
                log.trace(message);
            }
            case "WARN" -> {
                log.warn(message);
            }
            default -> log.info(message);
        }
        return message;
    }
}
