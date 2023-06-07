package com.example.qrgenerator.service;

import com.google.zxing.WriterException;
import java.io.IOException;

public interface QrService {

    byte[] generateQr(String barcodeText) throws WriterException, IOException;

}
