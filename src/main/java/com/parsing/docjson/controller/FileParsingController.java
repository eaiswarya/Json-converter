package com.parsing.docjson.controller;

import com.parsing.docjson.service.FileParsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/convert")
@RequiredArgsConstructor
public class FileParsingController {

    private final FileParsingService fileParsingService;

    @PostMapping("/upload")
    public ResponseEntity<String>parseWord(@RequestParam("file") MultipartFile file) {
        return fileParsingService.parseWordToJson(file);
    }
}
