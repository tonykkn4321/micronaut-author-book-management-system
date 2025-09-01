package com.example.app.routes;

import com.example.app.models.Author;
import com.example.app.repositories.AuthorRepository;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Controller("/authors")
public class AuthorController {

    @Inject
    AuthorRepository authorRepo;

    @Get("/")
    public List<Author> list() {
        return authorRepo.findAll();
    }

    @Post("/")
    public Author create(@Body Author author) {
        return authorRepo.save(author);
    }

    @Get("/{id}")
    public Object get(Long id) {
        return authorRepo.findById(id).orElse(new ErrorResponse("Author not found"));
    }

    @Put("/{id}")
    @Patch("/{id}")
    public Object update(Long id, @Body Author updated) {
        Optional<Author> existing = authorRepo.findById(id);
        if (existing.isEmpty()) return new ErrorResponse("Author not found");

        Author author = existing.get();
        if (updated.getFirstName() != null) author.setFirstName(updated.getFirstName());
        if (updated.getLastName() != null) author.setLastName(updated.getLastName());

        return authorRepo.update(author);
    }

    @Delete("/{id}")
    public Object delete(Long id) {
        if (authorRepo.existsById(id)) {
            authorRepo.deleteById(id);
            return new SuccessResponse("Author deleted");
        }
        return new ErrorResponse("Author not found");
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
