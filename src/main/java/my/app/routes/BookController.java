package my.app.routes;

import io.micronaut.http.annotation.*;
import my.app.models.Book;
import my.app.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Controller("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Get("/")
    public Iterable<Book> listAll() {
        return bookRepository.findAll();
    }

    @Get("/{id}")
    public Optional<Book> getById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }

    @Get("/by-author/{authorId}")
    public List<Book> getByAuthor(@PathVariable Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Post("/")
    public Book create(@Body Book book) {
        return bookRepository.save(book);
    }

    @Put("/{id}")
    public Book update(@PathVariable Long id, @Body Book updated) {
        return bookRepository.update(updated);
    }

    @Delete("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
