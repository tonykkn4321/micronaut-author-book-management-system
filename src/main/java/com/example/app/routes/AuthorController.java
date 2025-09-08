package com.example.app.routes;

import com.example.app.models.Author;
import com.example.app.repositories.AuthorRepository;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import java.util.List;

@Controller("/authors")
public class AuthorController {

    @Inject
    AuthorRepository authorRepo;

    @Get("/{id}")
    public Author getById(@PathVariable Long id) {
        return authorRepo.findById(id).orElse(null);
    }

    @Get
    public List<Author> listAll() {
        return authorRepo.findAll();
    }

    @Post
    public Author create(@Body Author author) {
        return authorRepo.save(author);
    }

    @Put("/{id}")
    public Author update(@PathVariable Long id, @Body Author updated) {
        updated.setId(id);
        return authorRepo.update(updated);
    }

    @Patch("/{id}")
    public Author patch(@PathVariable Long id, @Body Author partial) {
        return authorRepo.findById(id).map(existing -> {
            if (partial.getFirstName() != null) existing.setFirstName(partial.getFirstName());
            if (partial.getLastName() != null) existing.setLastName(partial.getLastName());
            return authorRepo.update(existing);
        }).orElse(null);
    }

    @Delete("/{id}")
    public void delete(@PathVariable Long id) {
        authorRepo.deleteById(id);
    }
}
