package com.example.app.routes;

import com.example.app.models.Book;
import com.example.app.models.Author;
import com.example.app.repositories.BookRepository;
import com.example.app.repositories.AuthorRepository;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Controller("/books")
public class BookController {

    @Inject
    BookRepository bookRepo;

    @Inject
    AuthorRepository authorRepo;

    @Get("/")
    public List<Book> list() {
        return bookRepo.findAll();
    }

    @Post("/")
    public Object create(@Body Book book) {
        Optional<Author> author = authorRepo.findById(book.getAuthor().getId());
        if (author.isEmpty()) return new ErrorResponse("Author not found");

        book.setAuthor(author.get());
        return bookRepo.save(book);
    }

    @Get("/{id}")
    public Object get(Long id) {
        return bookRepo.findById(id).orElse(new ErrorResponse("Book not found"));
    }

    @Put("/{id}")
    @Patch("/{id}")
    public Object update(Long id, @Body Book updated) {
        Optional<Book> existing = bookRepo.findById(id);
        if (existing.isEmpty()) return new ErrorResponse("Book not found");

        Book book = existing.get();
        if (updated.getTitle() != null) book.setTitle(updated.getTitle());
        if (updated.getYear() != null) book.setYear(updated.getYear());
        if (updated.getAuthor() != null && updated.getAuthor().getId() != null) {
            Optional<Author> author = authorRepo.findById(updated.getAuthor().getId());
            if (author.isEmpty()) return new ErrorResponse("Author not found");
            book.setAuthor(author.get());
        }

        return bookRepo.update(book);
    }

    @Delete("/{id}")
    public Object delete(Long id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
            return new SuccessResponse("Book deleted");
        }
        return new ErrorResponse("Book not found");
    }

    @Get("/author/{authorId}")
    public List<Book> getByAuthor(Long authorId) {
        return bookRepo.findByAuthorId(authorId);
    }

    static class ErrorResponse {
        public final String error;
        public ErrorResponse(String error) { this.error = error; }
    }

    static class SuccessResponse {
        public final String message;
        public SuccessResponse(String message) { this.message = message; }
    }
}
