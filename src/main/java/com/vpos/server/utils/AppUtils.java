package com.vpos.server.utils;

/*
 * @created 11/04/2023 - 3:26 AM
 * @project server
 * @author Rithy SKUN
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtils {
    public static String format(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
}
