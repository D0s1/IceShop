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
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String zutat
    ) {
        CsvFilter filter = new CsvFilter();
        if (scoreabove != null) {filter.setScoreabove(scoreabove);}
        if (user != null){filter.setSorte(user);}
        if (zutat != null){filter.setZutat(zutat);}
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

    @PatchMapping("/records/{id}/zutaten")
    public IceDataset addZutat(
            @PathVariable String id,
            @RequestParam String zutat
    ) {
        IceDataset record = csvData.getRecords().stream().filter(elem -> elem.getSorte().equals(id)).findFirst().orElse(null);
        assert record != null;
        record.getZutaten().add(zutat);
        return record;
    }
}