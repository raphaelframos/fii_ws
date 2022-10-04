package com.raphaelframos.refii.utils;

import org.jsoup.nodes.Element;

import static com.raphaelframos.refii.utils.ConstantsUtils.NOT_FOUND_MESSAGE;

public class SoupUtils {

    public static String text(Element element){
        String result = "";
        try {
            result = element.text();
        }catch (Exception e){
            result = NOT_FOUND_MESSAGE;
        }
        return result;
    }

    public static boolean isValid(String value, String other) {
        return !value.equals(NOT_FOUND_MESSAGE) || !other.equals(NOT_FOUND_MESSAGE);
    }
}
