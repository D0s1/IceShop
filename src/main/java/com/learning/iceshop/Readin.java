package com.learning.iceshop;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional.ofNullable(scoreabove).ifPresent(filter::setScoreabove);
        Optional.ofNullable(sorte).ifPresent(filter::setSorte);
        Optional.ofNullable(zutat).ifPresent(filter::setZutat);
        Optional.ofNullable(pricebelow).ifPresent(filter::setPriceBelow);
        return filter;
    }
    @GetMapping
    public List<String[]> getAllRecords(@ModelAttribute CsvFilter filter) {
        return csvData.getRecordsAsString(filter);
    }

    @GetMapping("/sorte")
    public List<String> getSorte(@ModelAttribute CsvFilter filter) {
        return csvData.getColumnA(filter);
    }

    @GetMapping("/score")
    public List<Integer> getScore(@ModelAttribute CsvFilter filter) {
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