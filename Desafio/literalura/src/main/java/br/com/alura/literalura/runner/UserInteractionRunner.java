package br.com.alura.literalura.runner;

import br.com.alura.literalura.model.Book;
import br.com.alura.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class UserInteractionRunner implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bem-vindo ao LiterAlura! \nAEscolha uma opção:");
            System.out.println("1. Buscar livros");
            System.out.println("2. Salvar livros no banco de dados");
            System.out.println("3. Listar todos os livros");
            System.out.println("4. Filtrar livros por autor");
            System.out.println("5. Pesquisar livros por título");
            System.out.println("6. Sair");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    Book[] books = bookService.fetchBooksFromApi();
                    System.out.println("Livros buscados.");
                    break;
                case 2:
                    bookService.saveBooks(Book);
                    System.out.println("Livros salvos no banco de dados.");
                    break;
                case 3:
                    List<Book> allBooks = bookService.getAllBooks();
                    printBookDetails(allBooks);
                    break;
                case 4:
                    System.out.println("Digite o nome do autor:");
                    String author = scanner.nextLine();
                    List<Book> booksByAuthor = bookService.getBooksByAuthor(author);
                    printBookDetails(booksByAuthor);
                    break;
                case 5:
                    System.out.println("Digite o título ou parte do título do livro:");
                    String keyword = scanner.nextLine();
                    List<Book> booksByTitle = bookService.searchBooksByTitle(keyword);
                    printBookDetails(booksByTitle);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!!!");
            }
        }
    }

    private void printBookDetails(List<Book> books) {
        for (Book book : book) {
            System.out.printf("%s - %s - %s - %s - %s - %d páginas - %s%n",
                    book.getTitle(), book.getAuthor(), book.getGenre(),
                    book.getPublicationDate(), book.getPublisher(),
                    book.getPages(), book.getIsbn());
        }
    }
}