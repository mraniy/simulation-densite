package com.kata.happn.service;

import com.kata.happn.model.Point;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileTransformer {

    public List<Point> readtsvFile(String filePath)  {
        TsvParserSettings settings = new TsvParserSettings();
        TsvParser parser = new TsvParser(settings);
        InputStream inputStream = FileTransformer.class.getResourceAsStream(filePath);
        List<String[]> allRows = parser.parseAll(inputStream);
        return allRows.stream()
                .map(strings ->
                     new Point(strings[0], Float.valueOf(strings[1]), Float.valueOf(strings[2]))
                ).collect(Collectors.toList());
    }


    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
