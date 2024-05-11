package com.example.cachingdemo.service;

import com.example.cachingdemo.Entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    public Book save(Book book);
    public Book getById(Long Id);
    Book updateBook(Book book);
    void deleteById(Long Id);
}
