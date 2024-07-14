package com.example.simplecrawler.module.crawler.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CrawlerController {
    private final CrawlerService crawlerService;

    @GetMapping("/parsing")
    public ResponseEntity getParsing(@RequestParam String url) throws IOException {
        return new ResponseEntity<>(crawlerService.runCrawling(url), HttpStatus.OK);
    }

    @GetMapping("/parsing/v2")
    public ResponseEntity getParsingV2(@RequestParam String url) throws IOException {
        return new ResponseEntity<>(crawlerService.runCrawlingV2(url), HttpStatus.OK);
    }
}
