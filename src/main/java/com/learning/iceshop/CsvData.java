package com.learning.iceshop;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class CsvData {

    private final List<String[]> records = new ArrayList<>();

    @PostConstruct
    public void init() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("data.csv")) {
            if (is == null) throw new FileNotFoundException("data.csv nicht gefunden!");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                br.readLine();
                String line;
                while ((line = br.readLine()) != null) {
                    records.add(line.split(","));
                }
            }
        }
    }

    public List<String> getColumnA() {
        return records.stream().map(r -> r[0]).toList();
    }

    public List<String> getColumnB() {
        return records.stream().map(r -> r[1]).toList();
    }
}
