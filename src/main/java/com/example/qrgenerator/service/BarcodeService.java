package com.example.qrgenerator.service;

import com.example.qrgenerator.exception.ValidatorException;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface BarcodeService {

    byte[] generateEANBarcode(String barcodeData) throws WriterException, IOException, ValidatorException;

    byte[] generateRandom() throws ValidatorException, IOException, WriterException, NoSuchAlgorithmException;

}
