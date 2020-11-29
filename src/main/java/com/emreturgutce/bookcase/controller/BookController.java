package com.emreturgutce.bookcase.controller;

import com.emreturgutce.bookcase.model.Book;
import com.emreturgutce.bookcase.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {
    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, String>> createBook(@RequestBody Map<String, Object> bookMap) {
        String name = (String) bookMap.get("name");
        String author_id = (String) bookMap.get("author_id");

        Book book = bookService.create(name, author_id);

        Map<String, String> map = new HashMap<>();

        map.put("id", book.getId());
        map.put("name", book.getName());
        map.put("author_id", book.getAuthor_id());
        map.put("created_at", book.getCreated_at());
        map.put("updated_at", book.getUpdated_at());

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, List<Book>>> findAllBooks()  {
        List<Book> books = bookService.findAll();

        Map<String, List<Book>> map = new HashMap<>();

        map.put("books", books);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Map<String, Book>> findBookById(@PathVariable("bookId") UUID bookId) {
        Book book = bookService.findById(bookId);

        Map<String, Book> map = new HashMap<>();

        map.put("book", book);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<HttpStatus> updateBook(@PathVariable("bookId") UUID bookId, @RequestBody Book book) {
        bookService.update(bookId, book);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
