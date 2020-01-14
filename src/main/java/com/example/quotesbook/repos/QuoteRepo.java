package com.example.quotesbook.repos;

import com.example.quotesbook.domain.Quote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepo extends CrudRepository<Quote, Long> {
    List<Quote> findByAuthor(String author);
}
