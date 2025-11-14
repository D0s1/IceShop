package com.learning.iceshop;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class IceDataset {
    @Getter @Setter
    private String sorte;
    @Getter @Setter
    private int score;
    @Getter @Setter
    private List<String> zutaten;
    public IceDataset(String sorte, int score, List<String>  zutaten) {
        this.score = score;
        this.sorte = sorte;
        this.zutaten = new ArrayList<>(zutaten);
    }

}
