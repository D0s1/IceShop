package com.learning.iceshop;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")

public class Readin {

    private final CsvData csvData;

    // Konstruktor-Injection: Spring injiziert die Bean automatisch
    public Readin(CsvData csvData) {
        this.csvData = csvData;
    }

    @ModelAttribute
    public CsvFilter loadFilters(
            @RequestParam(required = false) Integer scoreabove,
            @RequestParam(required = false) String user
    ) {
        CsvFilter filter = new CsvFilter();
        if (scoreabove != null) {filter.setScoreabove(scoreabove);}
        if (user != null){filter.setSorte(user);}
        return filter;
    }
    @GetMapping
    public List<String[]> getAllRecords(@ModelAttribute CsvFilter filter) {
        return csvData.getRecordsAsString(filter);
    }

    @GetMapping("/columnA")
    public List<String> getColumnA(@ModelAttribute CsvFilter filter) {
        return csvData.getColumnA(filter);
    }

    @GetMapping("/columnB")
    public List<Integer> getColumnB(@ModelAttribute CsvFilter filter) {
        return csvData.getColumnB(filter);
    }
}