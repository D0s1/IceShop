package com.learning.iceshop;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")

public class Readin {

    private final CsvData csvData;

    public Readin(CsvData csvData) {
        this.csvData = csvData;
    }

    @ModelAttribute
    public CsvFilter loadFilters(
            @RequestParam(required = false) Integer scoreabove,
            @RequestParam(required = false) String sorte,
            @RequestParam(required = false) String zutat,
            @RequestParam(required = false) Float pricebelow
    ) {
        CsvFilter filter = new CsvFilter();
        if (scoreabove != null) {filter.setScoreabove(scoreabove);}
        if (sorte != null){filter.setSorte(sorte);}
        if (zutat != null){filter.setZutat(zutat);}
        if (pricebelow != null){filter.setPriceBelow(pricebelow);}
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