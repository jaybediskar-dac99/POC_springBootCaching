package com.example.cachingdemo.controller;

import com.example.cachingdemo.Entity.Book;
import com.example.cachingdemo.annotations.ResponseTimeCalculator;
import com.example.cachingdemo.service.BookService;
import com.example.cachingdemo.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add/v1")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book b = bookService.save(book);
        return ResponseEntity.ok(b);
    }

    @ResponseTimeCalculator
    @GetMapping("/getBookById/v1")
    public ResponseEntity<Book> getById(@RequestParam Long id){
        Book b = bookService.getById(id);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping("/deleteBook/v1")
    public ResponseEntity<String> deleteBook(@RequestParam Long id){
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateBook/v1")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Book b = bookService.updateBook(book);
        return ResponseEntity.ok(b);
    }

}
