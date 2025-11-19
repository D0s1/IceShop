package com.learning.iceshop;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping("/api/records")

public class Readin {

    private final IceSortRepository iceSortRepository;
    private final CsvData csvData;

    public Readin(IceSortRepository iceSortRepository, CsvData csvData) {
        this.iceSortRepository = iceSortRepository;
        this.csvData = csvData;
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
    @GetMapping("/db")
    public List<IceSort> getAllIceSOrt(@ModelAttribute IceSortFilter filter){
        return iceSortRepository.findAll(IceSortSpecs.buildSpec(filter));
    }

    @GetMapping("/scoreabove{id}")
    public List<IceSort> getScoreAbove(@PathVariable int id){
        return iceSortRepository.findByScoreGreaterThan(id);
    }

    @GetMapping("/filter/names")
    public List<String> getNamesAboveScore(@RequestParam int score) {
        return iceSortRepository.findIceSortsByScoreGreaterThan(score);
    }

    @PostMapping("/add")
    public IceSort addIceSort(@RequestBody IceSort iceSort) {
        iceSort.setingredients(iceSort.getingredients());
        return iceSortRepository.save(iceSort);
    }
}