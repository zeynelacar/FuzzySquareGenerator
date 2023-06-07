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

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/barcode")
public class BarcodeController {

    private final BarcodeService barcodeService;

    @PostMapping(value = "/generate/{barcodeRequest}",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateEANBarcode(@Valid @PathVariable String barcodeRequest ) throws IOException, WriterException, ValidatorException {
        return new ResponseEntity<>(barcodeService.generateEANBarcode(barcodeRequest), HttpStatus.CREATED);
    }

    @PostMapping(value = "/random",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateEANRandom() throws IOException, WriterException, ValidatorException {
        return new ResponseEntity<>(barcodeService.generateRandom(), HttpStatus.CREATED);
    }

}
