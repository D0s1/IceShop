package com.learning.iceshop;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/records")

public class Readin {

    private final CsvData csvData;

    // Konstruktor-Injection: Spring injiziert die Bean automatisch
    public Readin(CsvData csvData) {
        this.csvData = csvData;
    }

    @GetMapping
    public List<String[]> getAllRecords() {
        return csvData.getRecords();
    }

    @GetMapping("/columnA")
    public List<String> getColumnA() {
        return csvData.getColumnA();
    }

    @GetMapping("/columnB")
    public List<String> getColumnB() {
        return csvData.getColumnB();
    }
}