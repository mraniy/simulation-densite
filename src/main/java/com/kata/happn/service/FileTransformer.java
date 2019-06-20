package com.kata.happn.service;

import com.kata.happn.model.Point;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileTransformer implements FileExtractor {

    public List<Point> readFile(String filePath)  {
        TsvParserSettings settings = new TsvParserSettings();
        TsvParser parser = new TsvParser(settings);
        InputStream inputStream = FileTransformer.class.getResourceAsStream(filePath);
        List<String[]> allRows = parser.parseAll(inputStream);
        return allRows.stream()
                .map(strings ->
                     new Point(strings[0], Float.valueOf(strings[1]), Float.valueOf(strings[2]))
                ).collect(Collectors.toList());
    }

}
