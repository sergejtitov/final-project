package com.htp.utils;

import com.htp.exceptions.CustomValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUtils {

    public static long validatePath(String pathId){
        long entityId;
        try{
            entityId = Long.parseLong(pathId);
        } catch (NumberFormatException e){
            log.error(e.getMessage(), e);
            throw new CustomValidationException("Illegal path!");
        }
        return entityId;
    }

    public static int validateOffset (String offset){
        int offsetInt;
        try {
            offsetInt = Integer.parseInt(offset);
        }  catch (NumberFormatException e){
            log.error(e.getMessage(), e);
            throw new CustomValidationException("Illegal path!");
        }
        return offsetInt;
    }

    public static double validateFinalAmount(String finalAmount){
        double finalAmountDouble;
        try {
            finalAmountDouble = Double.parseDouble(finalAmount);
        } catch (NumberFormatException e){
            log.error(e.getMessage(), e);
            throw new CustomValidationException("Illegal path!");
        }
        return finalAmountDouble;
    }
}
