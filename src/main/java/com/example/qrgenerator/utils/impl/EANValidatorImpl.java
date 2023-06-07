package com.example.qrgenerator.utils.impl;
import com.example.qrgenerator.utils.EANValidator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class EANValidatorImpl implements EANValidator {

    public Map<String,Object> validate(String code){
        HashMap<String,Object> map = new HashMap<>();
        int digit= -1;
        boolean valid = false;
        if(isValidLength(code)){
            valid = true;
            if(willCheckDigitBeCalculated(code)){
                digit = calculateCheckDigit(code);
            }
        }
        map.put("checkDigit",digit);
        map.put("validationResult",valid);
        return map;

    }

    public boolean isValidLength(String barcode) {
        return Arrays.asList(12,13).contains(barcode.length());
    }

    public boolean willCheckDigitBeCalculated(String barcode){
        return barcode.length() == 12;
    }

    public static int calculateCheckDigit(String barcode){
        return 10 - (sumOdds(barcode) + (3 * sumEvens(barcode))) % 10;

    }

    private static int sumOdds(String str){
        int res = 0;
        for(int i = 0;i<12;i=i+2){
            res += str.charAt(i) - '0';
        }
        return res;
    }

    private static int sumEvens(String str){
        int res = 0;
        for(int i = 1;i<12;i=i+2){
            res += str.charAt(i) - '0';
        }
        return res;
    }
}
