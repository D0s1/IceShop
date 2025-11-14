package com.learning.iceshop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CsvFilter {
    List<Predicate<IceDataset>> predicates;
    public CsvFilter(){
        predicates = new ArrayList<>();
    }
    public void setScoreabove(Integer scoreabove) {
        predicates.add(value -> value.getScore() > scoreabove);
    }

    public void setSorte(String sorte) {
        predicates.add(value -> value.getSorte().equals(sorte));
    }
    public void setZutat(String zutat) {
        predicates.add(value -> Arrays.asList(value.getZutaten()).contains(zutat));
    }

    public Predicate<IceDataset> buildPredicate() {
        return predicates.stream()
                .reduce(value -> true, Predicate::and);
    }

    public Stream<IceDataset> applyFilter(List <IceDataset> data) {
        Predicate<IceDataset> combined = buildPredicate();
        return data.stream().filter(combined);
    }
}
