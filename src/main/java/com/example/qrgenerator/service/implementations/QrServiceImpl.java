package com.example.qrgenerator.service.implementations;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.example.qrgenerator.service.QrService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QrServiceImpl implements QrService {

    @Override
    public byte[] generateQr(String barcodeText) throws WriterException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix bitMap = qrWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 250, 250);
        ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMap),"png",buffer);
        return buffer.toByteArray();
    }
}
