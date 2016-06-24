package com.exist.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.validator.GenericValidator;

public class Validator{
    
    public static boolean isValidId(String text){
        if(GenericValidator.isLong(text)){
            return GenericValidator.minValue(Long.parseLong(text), 1L);
        }
        return false;
    }
    
    public static boolean isValidText(String text){
        int minLength = 1;
        int maxLength = 255;
        return isLengthInRange(text, minLength, maxLength);
    }
    
    public static boolean isValidChoice(String choice, String[] availableChoices){
        return Arrays.asList(availableChoices).contains(choice);
    }
    
    public static boolean areValidChoices(String[] choices, String[] availableChoices){
        return Arrays.asList(availableChoices).containsAll(Arrays.asList(choices));
    }
    
    public static boolean isValidZipCode(String text){
        return GenericValidator.matchRegexp(text, "^\\d{4}$");
    }
    
    public static boolean isValidGwa(String text){
        float minGwa = 50f;
        float maxGwa = 1005;
        float gwa = 0;
        if(GenericValidator.isFloat(text)){
            gwa = Float.parseFloat(text);
        }
        else{
            return false;
        }
        return GenericValidator.isInRange(gwa, minGwa, maxGwa);
    }
    
    public static boolean isValidDate(String text){
        String format = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if(GenericValidator.isDate(text, format, false)){
            Calendar now = Calendar.getInstance();
            now.set(Calendar.HOUR_OF_DAY, 0);
            Date today = now.getTime();
            Date oldDate = null;
            Date currentDate = null;
            try{
                oldDate = sdf.parse("1/1/1900");
                currentDate = sdf.parse(text);
            }
            catch(ParseException ex){
                ex.printStackTrace();
            }
            
            if(currentDate.after(today) || currentDate.before(oldDate)){
                return false;
            }
            return true;
        }
        return false;
    }
    
    public static boolean isValidEmail(String text){
        return GenericValidator.isEmail(text);
    }
    
    public static boolean isValidMobileNumber(String text){
        return GenericValidator.matchRegexp(text, "^(09|\\+639)\\d{9}$");
    }
    
    public static boolean isValidLandlineNumber(String text){
        return GenericValidator.matchRegexp(text, "^\\d{7}$");
    }
    
    private static boolean isLengthInRange(String text, int minLength, int maxLength){
        return GenericValidator.minLength(text, minLength) && 
               GenericValidator.maxLength(text, maxLength) &&
               !GenericValidator.isBlankOrNull(text);
    }
}
