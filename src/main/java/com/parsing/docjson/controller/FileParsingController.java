package com.parsing.docjson.controller;

import com.parsing.docjson.service.FileParsingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/parse")
public class FileParsingController {

    public final FileParsingService fileParsingService;

    public FileParsingController(FileParsingService fileParsingService) {
        this.fileParsingService = fileParsingService;
    }

    @PostMapping("/upload")
    public String parse(@RequestParam("file") MultipartFile file) throws Exception {
        fileParsingService.parse(file);
        return "File uploaded successfully";
    }
}
