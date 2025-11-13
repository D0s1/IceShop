package com.learning.iceshop;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    public List<String[]> getAllRecords(@RequestParam(required = false) Integer scoreabove) {
        return csvData.getRecordsAsString(scoreabove);
    }

    @GetMapping("/columnA")
    public List<String> getColumnA() {
        return csvData.getColumnA();
    }

    @GetMapping("/columnB")
    public List<Integer> getColumnB() {
        return csvData.getColumnB();
    }
}