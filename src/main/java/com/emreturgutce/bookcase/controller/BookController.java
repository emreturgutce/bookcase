package com.emreturgutce.bookcase.controller;

import com.emreturgutce.bookcase.model.Book;
import com.emreturgutce.bookcase.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        Map<String, String> map = generateBookResponse(book);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> findAllBooks()  {
        List<Book> books = bookService.findAll();

        Map<String, String> map = new HashMap<>();

        map.put("books", books.toString());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Map<String, String>> findBookById(@PathVariable("bookId") UUID bookId) {
        Book book = bookService.findById(bookId);

        Map<String, String> map = generateBookResponse(book);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Map<String, String>> updateBook(@PathVariable("bookId") UUID bookId, @RequestBody Book bookParam) {
        Book book = bookService.update(bookId, bookParam);

        Map<String, String> map = generateBookResponse(book);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable("bookId") UUID bookId) {
        bookService.delete(bookId);

        Map<String, String> map = new HashMap<>();

        map.put("message", "Book deleted successfully with the given id");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
//
//    @GetMapping("/{bookId}/add")
//    public ResponseEntity<HttpStatus> addBookToFavorites(HttpServletRequest request, @PathVariable("bookId") UUID bookId) {
//        UUID userId = UUID.fromString(request.getAttribute("id").toString());
//        users_booksService.create(userId, bookId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    private Map<String, String> generateBookResponse(Book book) {
        Map<String, String> map = new HashMap<>();

        map.put("id", book.getId().toString());
        map.put("name", book.getName());
        map.put("created_at", book.getCreated_at().toString());
        map.put("updated_at", book.getUpdated_at().toString());

        return map;
    }
}
