package br.com.alura.literalura.controller;

import br.com.alura.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<br.com.alura.literalura.model.Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/author/{author}")
    public List<br.com.alura.literalura.model.Book> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/search")
    public List<br.com.alura.literalura.model.Book> searchBooksByTitle(@RequestParam String keyword) {
        return bookService.searchBooksByTitle(keyword);
    }

    @PostMapping("/fetch")
    public void fetchAndSaveBooks() {
        Book[] books = bookService.fetchBooksFromApi();
        bookService.saveBooks(books);
    }
}
