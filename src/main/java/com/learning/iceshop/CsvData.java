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
import java.util.stream.Stream;

@Getter
@Component
public class CsvData {

    private final List<IceDataset> records = new ArrayList<>();

    @PostConstruct
    public void init() throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("data.csv")) {
            if (is == null) throw new FileNotFoundException("data.csv nicht gefunden!");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                br.readLine();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = (line.split(","));
                    records.add(new IceDataset(values[0], Integer.parseInt(values[1])));
                }
            }
        }
    }

    public List<String> getColumnA(CsvFilter filter) {
        return filter.applyFilter(records).map(IceDataset::getSorte).toList();
    }

    public List<Integer> getColumnB(CsvFilter filter) {
        return filter.applyFilter(records).map(IceDataset::getScore).toList();
    }

    public List<String[]> getRecordsAsString(CsvFilter filter) {
        List<String[]> output = new ArrayList<>();
        filter.applyFilter(records).forEach(elem -> output.add(new String[] {elem.getSorte(), String.valueOf(elem.getScore())}));
         return output;
    }
}
