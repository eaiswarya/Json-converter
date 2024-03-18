package com.parsing.docjson.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileParsingService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public  ResponseEntity<String>parseWordToJson(MultipartFile file) {
        try {
            XWPFDocument doc = new XWPFDocument(file.getInputStream());
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            List<String> paragraphTexts = paragraphs.stream()
                    .map(XWPFParagraph::getText)
                    .collect(Collectors.toList());
            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(paragraphTexts);
            kafkaTemplate.send("doc-json", json);
            return ResponseEntity.ok("File uploaded");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error parsing");
        }
    }

}
