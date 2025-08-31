package com.example;

import com.example.entity.Author;
import com.example.repository.AuthorRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class BookManagementTest {

    @Inject
    AuthorRepository authorRepository;

    @Test
    void testCreateAuthor() {
        Author author = new Author();
        author.setFirstName("Test");
        author.setLastName("Author");

        Author saved = authorRepository.save(author);
        assertNotNull(saved.getId());
    }
}
