package com.example.qrgenerator.controller;

import com.example.qrgenerator.dto.RequestDTO;
import com.example.qrgenerator.exception.ValidatorException;
import com.example.qrgenerator.service.BarcodeService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/barcode")
public class BarcodeController {

    private final BarcodeService barcodeService;

    @PostMapping(value = "/generate",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateEANBarcode(@Valid @RequestBody RequestDTO req) throws IOException, WriterException, ValidatorException {
        return new ResponseEntity<>(barcodeService.generateEANBarcode(req.getData()), HttpStatus.CREATED);
    }

    @PostMapping(value = "/random",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateEANRandom() throws IOException, WriterException, ValidatorException, NoSuchAlgorithmException {
        return new ResponseEntity<>(barcodeService.generateRandom(), HttpStatus.CREATED);
    }

}
