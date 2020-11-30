package com.emreturgutce.bookcase.controller;

import com.emreturgutce.bookcase.model.Book;
import com.emreturgutce.bookcase.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createBook(@RequestBody Map<String, Object> bookMap) {
        String name = (String) bookMap.get("name");
        UUID author_id = UUID.fromString(bookMap.get("author_id").toString());

        Book book = bookService.create(name, author_id);

        Map<String, String> map = new HashMap<>();

        map.put("name", book.getName());

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
//
//    @GetMapping("")
//    public ResponseEntity<Map<String, List<Book>>> findAllBooks()  {
//        List<Book> books = bookService.findAll();
//
//        Map<String, List<Book>> map = new HashMap<>();
//
//        map.put("books", books);
//
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Map<String, String>> findBookById(@PathVariable("bookId") UUID bookId) {
        Book book = bookService.findById(bookId);

        Map<String, String> map = new HashMap<>();

        map.put("name", book.getName());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

//    @PutMapping("/{bookId}")
//    public ResponseEntity<HttpStatus> updateBook(@PathVariable("bookId") UUID bookId, @RequestBody Book book) {
//        bookService.update(bookId, book);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{bookId}")
//    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("bookId") UUID bookId) {
//        bookService.delete(bookId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/{bookId}/add")
//    public ResponseEntity<HttpStatus> addBookToFavorites(HttpServletRequest request, @PathVariable("bookId") UUID bookId) {
//        UUID userId = UUID.fromString(request.getAttribute("id").toString());
//        users_booksService.create(userId, bookId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
