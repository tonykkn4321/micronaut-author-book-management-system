package my.app.controllers;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import my.app.models.Author;
import my.app.repositories.AuthorRepository;

import java.util.*;

@Controller("/authors")
public class AuthorController {

    @Inject
    AuthorRepository authorRepo;

    @Get
    public Iterable<Author> list() {
        return authorRepo.findAll();
    }

    @Get("/{id}")
    public Optional<Author> get(Long id) {
        return authorRepo.findById(id);
    }

    @Post
    public Author create(@Body Author author) {
        return authorRepo.save(author);
    }

    @Put("/{id}")
    public Author update(Long id, @Body Author author) {
        author.setId(id);
        return authorRepo.update(author);
    }

    @Patch("/{id}")
    public Author patch(Long id, @Body Author author) {
        Author existing = authorRepo.findById(id).orElseThrow();
        if (author.getFirstName() != null) existing.setFirstName(author.getFirstName());
        if (author.getLastName() != null) existing.setLastName(author.getLastName());
        return authorRepo.update(existing);
    }

    @Delete("/{id}")
    public void delete(Long id) {
        authorRepo.deleteById(id);
    }
}
