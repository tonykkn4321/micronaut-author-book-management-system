package com.example.controller;

import com.example.entity.Author;
import com.example.repository.AuthorRepository;
import io.micronaut.http.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@Controller("/authors")
public class AuthorController {
    private final AuthorRepository authorRepo;

    public AuthorController(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Get
    public Iterable<Author> list() {
        return authorRepo.findAll();
    }

    @Post
    public Author create(@Body @Valid Author author) {
        return authorRepo.save(author);
    }

    @Get("/{id}")
    public Optional<Author> get(Long id) {
        return authorRepo.findById(id);
    }

    @Put("/{id}")
    public Author update(Long id, @Body Author updated) {
        updated.setId(id);
        return authorRepo.update(updated);
    }

    @Delete("/{id}")
    public void delete(Long id) {
        authorRepo.deleteById(id);
    }
}
