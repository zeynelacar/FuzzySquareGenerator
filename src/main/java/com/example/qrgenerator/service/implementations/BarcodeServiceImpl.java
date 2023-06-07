package com.example.qrgenerator.service.implementations;

import com.example.qrgenerator.exception.ValidatorException;
import com.example.qrgenerator.service.BarcodeService;
import com.example.qrgenerator.utils.EANValidator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BarcodeServiceImpl implements BarcodeService {

    private final EANValidator validator;

    @Override
    public byte[] generateEANBarcode(String barcodeData) throws WriterException, IOException, ValidatorException {
        Map<String,Object> res = validator.validate(barcodeData);
        if(!Boolean.parseBoolean(res.get("validationResult").toString())){
            throw new ValidatorException("Data can not be validated");
        }
        int digit = Integer.parseInt(res.get("checkDigit").toString());
        barcodeData = digit != -1 ? barcodeData + digit : barcodeData;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMap = barcodeWriter.encode(barcodeData, BarcodeFormat.EAN_13, 400, 200);
        ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMap),"png",buffer);
        return buffer.toByteArray();
    }

    @Override
    public byte[] generateRandom() throws ValidatorException, IOException, WriterException, NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstance("NativePRNG");
        StringBuilder sb = new StringBuilder();
        sb.append(rand.nextInt(9) + 1);
        for (int i = 1; i < 12; i++){
            sb.append(rand.nextInt(10));
        }
        return generateEANBarcode(sb.substring(0,12));
    }
}
