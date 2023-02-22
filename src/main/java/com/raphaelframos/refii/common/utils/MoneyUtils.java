package com.raphaelframos.refii.common.utils;

import java.math.BigDecimal;

public class MoneyUtils {
    public static BigDecimal stringToBigDecimal(String value) {
        BigDecimal result = BigDecimal.ZERO;
        try{
            result = new BigDecimal(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static int stringToInt(String value) {
        int result = 1;
        try {
            result = Integer.parseInt(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static BigDecimal intToBigDecimal(int amount) {
        BigDecimal result = BigDecimal.ZERO;
        try{
            result = new BigDecimal(amount);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
