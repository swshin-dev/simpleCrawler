package com.example.simplecrawler.module.crawler.application;

import com.example.simplecrawler.module.parser.ParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CrawlerService {
    private final ParserService parserService;

    public Map[] runCrawling(String url) throws IOException {
        List<Map.Entry<String, Integer>> parsing = parserService.parsing(url);

        return parsing.stream()
                .map(data -> {
                    return  Map.of(data.getKey(), data.getValue());
                })
                .toArray(Map[]::new);
    }

    public Map[] runCrawlingV2(String url) throws IOException {
        List<Map.Entry<String, Integer>> parsing = parserService.parsingV2(url);

        return parsing.stream()
                .map(data -> {
                    return  Map.of(data.getKey(), data.getValue());
                })
                .toArray(Map[]::new);
    }
}
