package br.com.alura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.author = :author")
    List<Book> findBooksByAuthor(@Param("author") String author);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword%")
    List<Book> searchBooksByTitle(@Param("keyword") String keyword);
}