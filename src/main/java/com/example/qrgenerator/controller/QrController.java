package com.example.qrgenerator.controller;

import com.example.qrgenerator.dto.RequestDTO;
import com.example.qrgenerator.service.QrService;
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
@RequestMapping("v1/qr")
public class QrController {

    private final QrService qrService;

    @PostMapping(value = "/generate",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQR(@Valid @RequestBody RequestDTO qrRequest ) throws WriterException, IOException {
        return new ResponseEntity<>(qrService.generateQr(qrRequest.getData()),HttpStatus.CREATED);
    }
}
