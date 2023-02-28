package com.raphaelframos.refii.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String date(LocalDateTime date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(format);
    }
}
