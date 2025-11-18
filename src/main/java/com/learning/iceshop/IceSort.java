package com.learning.iceshop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Entity
public class IceSort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;  // <-- Primärschlüssel muss numerisch sein

    @Getter @Setter
    private String iceSort;

    @Getter @Setter
    private int score;

    @Column(columnDefinition = "JSON")
    private String ingredients; // <-- JSON als String speichern

    public List<String> getingredients() {
        try {
            return new ObjectMapper().readValue(this.ingredients, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setingredients(List<String> list) {
        try {
            this.ingredients = (list == null) ? "[]" : new ObjectMapper().writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}