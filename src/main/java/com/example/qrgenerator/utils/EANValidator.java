package com.example.qrgenerator.utils;

import java.util.Map;

public interface EANValidator {
    Map<String,Object> validate(String code);
}
