package com.example.controller;

import com.example.entity.Book;
import com.example.repository.BookRepository;
import com.example.repository.AuthorRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller("/books")
public class BookController {
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    public BookController(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    @Get
    public Iterable<Book> list() {
        return bookRepo.findAll();
    }

    @Post
    public HttpResponse<?> create(@Body @Valid Book book) {
        if (authorRepo.findById(book.getAuthor().getId()).isEmpty()) {
            return HttpResponse.badRequest("Author does not exist");
        }
        return HttpResponse.created(bookRepo.save(book));
    }

    @Get("/{id}")
    public Optional<Book> get(Long id) {
        return bookRepo.findById(id);
    }

    @Put("/{id}")
    public Book update(Long id, @Body Book updated) {
        updated.setId(id);
        return bookRepo.update(updated);
    }

    @Delete("/{id}")
    public void delete(Long id) {
        bookRepo.deleteById(id);
    }

    @Get("/author/{authorId}")
    public List<Book> byAuthor(Long authorId) {
        return bookRepo.findByAuthorId(authorId);
    }
}
