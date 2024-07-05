package br.com.alura.literalura.service;

import br.com.alura.literalura.repository.BookRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookService {
    private final WebClient webClient;

    @Autowired
    private BookRepository bookRepository;

    public BookService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("URL_DA_API_DE_LIVROS").build();
    }

    public br.com.alura.literalura.model.Book[] fetchBooksFromApi() {
        String response = this.webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Gson gson = new Gson();
        return gson.fromJson(response, Book[].class);
    }

    public void saveBooks(Book[] books) {
        for (Book book : books) {
            bookRepository.save(book);
        }
    }

    public List<br.com.alura.literalura.model.Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<br.com.alura.literalura.model.Book> getBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthor(author);
    }

    public List<br.com.alura.literalura.model.Book> searchBooksByTitle(String keyword) {
        return bookRepository.searchBooksByTitle(keyword);
    }
}