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

    public Readin(IceSortRepository iceSortRepository) {
        this.iceSortRepository = iceSortRepository;
    }

    @GetMapping("/db")
    public List<IceSort> getAllIceSort(@ModelAttribute IceSortFilter filter){
        return iceSortRepository.findAll(IceSortSpecs.buildSpec(filter));
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