package my.app.controllers;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import my.app.models.Book;
import my.app.repositories.BookRepository;

import java.util.*;

@Controller("/books")
public class BookController {

    @Inject
    BookRepository bookRepo;

    @Get
    public Iterable<Book> list() {
        return bookRepo.findAll();
    }

    @Get("/{id}")
    public Optional<Book> get(Long id) {
        return bookRepo.findById(id);
    }

    @Post
    public Book create(@Body Book book) {
        return bookRepo.save(book);
    }

    @Put("/{id}")
    public Book update(Long id, @Body Book book) {
        book.setId(id);
        return bookRepo.update(book);
    }

    @Patch("/{id}")
    public Book patch(Long id, @Body Book book) {
        Book existing = bookRepo.findById(id).orElseThrow();
        if (book.getTitle() != null) existing.setTitle(book.getTitle());
        if (book.getYear() != null) existing.setYear(book.getYear());
        return bookRepo.update(existing);
    }

    @Delete("/{id}")
    public void delete(Long id) {
        bookRepo.deleteById(id);
    }
}
