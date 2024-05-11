package com.example.cachingdemo.service.impl;

import com.example.cachingdemo.Entity.Book;
import com.example.cachingdemo.repositories.BookRepository;
import com.example.cachingdemo.service.BookService;
import com.example.cachingdemo.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    RedisService redisService;

    /*@Override
    public Book getById(Long Id) {
        Book bookFromCache = redisService.get("book_"+Id.toString(),Book.class);
        if(bookFromCache != null){
            log.info("fetching book from cache");
            return bookFromCache;
        }else {
            log.info("Fetching book from db with id : {}", Id);
            Book bookFromDb =  bookRepository.findById(Id).orElseThrow(null);
            if(bookFromDb != null){
                redisService.set("book_"+Id.toString(),bookFromDb,1L);
            }
            return bookFromDb;
        }
    }*/

    @Override
    //@Cacheable(cacheNames = "books",key = "#p0",sync = true)
    public Book getById(Long Id) {
        //Firstly when Method is called , will check cache memory with
        //key-name=book and value of id = our id
        //If not found this id in cache then method will be called otherwise returned from cache
        //By default sync is false
        log.info("Fetching book from db with id : {}", Id);
        return bookRepository.findById(Id).orElseThrow(null);
    }


    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @CachePut(cacheNames = "book",key = "#p0.id")
    public Book updateBook(Book book) {
        //When we are updating any book with id, so we should update it in our cache also
        //otherwise we will get old book from cache though it is updated in db bcs cache is not updated
        //@CachePut updates cache
        return bookRepository.save(book);
    }


    @Override
    @CacheEvict(cacheNames = "book",key = "#id")
    public void deleteById(Long Id) {
        bookRepository.deleteById(Id);
    }

}
