package com.example.simplecrawler.module.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.springframework.stereotype.Service;
import scala.collection.Seq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParserService {
    public List<Map.Entry<String, Integer>> parsing(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements pageContentElements = document.select(".page-content");
        String text = "";

        for (Element element : pageContentElements) {
            text = element.text();
        }

        return wordCount(filterSpecialChar(text));
    }

    private List<Map.Entry<String, Integer>> wordCount(String text) {
        Seq<KoreanTokenizer.KoreanToken> tokenize = OpenKoreanTextProcessorJava.tokenize(text);
        List<String> strings = OpenKoreanTextProcessorJava.tokensToJavaStringList(tokenize);

        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : strings) {
            wordCount.put(word, wordCount.getOrDefault(word, 1) + 1);
        }

        return wordCount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .toList();
    }

    private List<Map.Entry<String, Integer>> wordCount2(String text) {
        String[] words = text.split("\\s+");

        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 1) + 1);
        }

        return wordCount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .toList();
    }

    public List<Map.Entry<String, Integer>> parsingV2(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements pageContentElements = document.select(".page-content");
        String text = "";

        for (Element element : pageContentElements) {
            text = element.text();
        }

        return wordCount2(filterSpecialChar(text));
    }

    private String filterSpecialChar(String text) {
        return text.replaceAll("[^a-zA-Z가-힣 ]", "");
    }

}
