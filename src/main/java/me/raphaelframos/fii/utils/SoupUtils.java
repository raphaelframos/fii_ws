package me.raphaelframos.fii.utils;

import org.jsoup.nodes.Element;

public class SoupUtils {

    public static String text(Element element){
        String result = "";
        try {
            result = element.text();
        }catch (Exception e){
            result = "Não encontrado";
        }
        return result;
    }
}
