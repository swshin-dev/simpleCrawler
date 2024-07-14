package com.example.simplecrawler.module.crawler.application.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CrawlerResponse {
    private String word;
    private int count;
}
