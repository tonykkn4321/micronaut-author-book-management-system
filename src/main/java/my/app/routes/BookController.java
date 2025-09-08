package my.app.routes;

import my.app.models.Book;
import my.app.repositories.BookRepository;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import java.util.List;

@Controller("/books")
public class BookController {

    @Inject
    BookRepository bookRepo;

    @Get("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookRepo.findById(id).orElse(null);
    }

    @Get
    public List<Book> listAll() {
        return bookRepo.findAll();
    }

    @Post
    public Book create(@Body Book book) {
        return bookRepo.save(book);
    }

    @Put("/{id}")
    public Book update(@PathVariable Long id, @Body Book updated) {
        updated.setId(id);
        return bookRepo.update(updated);
    }

    @Patch("/{id}")
    public Book patch(@PathVariable Long id, @Body Book partial) {
        return bookRepo.findById(id).map(existing -> {
            if (partial.getTitle() != null) existing.setTitle(partial.getTitle());
            if (partial.getYear() != null) existing.setYear(partial.getYear());
            if (partial.getAuthor() != null) existing.setAuthor(partial.getAuthor());
            return bookRepo.update(existing);
        }).orElse(null);
    }

    @Delete("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepo.deleteById(id);
    }
}
