package com.vpos.server.utils;

/*
 * @created 11/04/2023 - 3:26 AM
 * @project server
 * @author Rithy SKUN
 */

//import net.glxn.qrgen.core.image.ImageType;
//import net.glxn.qrgen.javase.QRCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
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

    //Generate random number
    public static UUID generateRandomValue() {
        UUID uuid=UUID.randomUUID();
        return uuid;
    }

    //    https://www.baeldung.com/java-generating-barcodes-qr-codes
//    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
//
//        ByteArrayOutputStream stream = QRCode
//                .from(barcodeText)
//                .withSize(250, 250)
//                .stream();
//        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
//
//        return ImageIO.read(bis);
//
////        return stream;
//    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }


    public static Object mergeObjects(Object source, Object target) throws Exception {
        Field[] allFields = source.getClass().getDeclaredFields();
        for (Field field : allFields) {
            if(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())){
                continue;
            }

            if (!field.isAccessible() && Modifier.isPrivate(field.getModifiers()))
                field.setAccessible(true);
            if (field.get(source) != null) {
                field.set(target, field.get(source));
            }
        }

        return target;
    }
}
