package com.example.quotesbook.controller;

import com.example.quotesbook.domain.Quote;
import com.example.quotesbook.domain.User;
import com.example.quotesbook.repos.QuoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private QuoteRepo quoteRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Quote> quotes = quoteRepo.findAll();
        model.put("quotes", quotes);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String author, Map<String, Object> model) {
        Quote quote = new Quote(text, author, user);
        quoteRepo.save(quote);

        Iterable<Quote> quotes = quoteRepo.findAll();
        model.put("quotes", quotes);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Quote> quotes;

        if (filter != null && !filter.isEmpty()) {
            quotes = quoteRepo.findByAuthor(filter);
        } else {
            quotes = quoteRepo.findAll();
        }

        model.put("quotes", quotes);

        return "main";
    }
}