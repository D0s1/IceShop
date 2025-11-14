package com.learning.iceshop;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class IceDataset {
    @Getter @Setter
    private String sorte;
    @Getter @Setter
    private int score;
    @Getter @Setter
    private String[] zutaten;
    public IceDataset(String sorte, int score, String[] zutaten) {
        this.score = score;
        this.sorte = sorte;
        this.zutaten = zutaten;

    }
}
