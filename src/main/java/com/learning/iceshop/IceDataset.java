package com.learning.iceshop;

import lombok.Getter;
import lombok.Setter;

public class IceDataset {
    @Getter @Setter
    private String sorte;
    @Getter @Setter
    private int score;
    public IceDataset(String sorte, int score){
        this.score = score;
        this.sorte = sorte;

    }
}
