package my.app.controllers;

import io.micronaut.http.annotation.*;
import my.app.models.Author;
import my.app.repositories.AuthorRepository;

import java.util.Optional;

@Controller("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Get("/")
    public Iterable<Author> listAll() {
        return authorRepository.findAll();
    }

    @Get("/{id}")
    public Optional<Author> getById(@PathVariable Long id) {
        return authorRepository.findById(id);
    }

    @Post("/")
    public Author create(@Body Author author) {
        return authorRepository.save(author);
    }

    @Put("/{id}")
    public Author update(@PathVariable Long id, @Body Author updated) {
        return authorRepository.update(updated);
    }

    @Delete("/{id}")
    public void delete(@PathVariable Long id) {
        authorRepository.deleteById(id);
    }
}
