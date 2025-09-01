package com.example;

import com.example.app.models.Author;
import com.example.app.models.Book;
import com.example.app.repositories.AuthorRepository;
import com.example.app.repositories.BookRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = true)
public class BookManagementTest {

    @Inject
    AuthorRepository authorRepo;

    @Inject
    BookRepository bookRepo;

    @Test
    void testCreateAuthor() {
        Author author = new Author();
        author.setFirstName("Test");
        author.setLastName("Author");

        Author saved = authorRepo.save(author);
        assertNotNull(saved.getId());
        assertEquals("Test", saved.getFirstName());
        assertEquals("Author", saved.getLastName());
    }

    @Test
    void testFindAuthorById() {
        Author author = new Author();
        author.setFirstName("Jane");
        author.setLastName("Doe");
        Author saved = authorRepo.save(author);

        Optional<Author> found = authorRepo.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Jane", found.get().getFirstName());
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("Old");
        author.setLastName("Name");
        Author saved = authorRepo.save(author);

        saved.setFirstName("New");
        Author updated = authorRepo.update(saved);
        assertEquals("New", updated.getFirstName());
    }

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("ToDelete");
        author.setLastName("Me");
        Author saved = authorRepo.save(author);

        authorRepo.deleteById(saved.getId());
        assertFalse(authorRepo.existsById(saved.getId()));
    }

    @Test
    void testCreateBook() {
        Author author = new Author();
        author.setFirstName("Book");
        author.setLastName("Author");
        Author savedAuthor = authorRepo.save(author);

        Book book = new Book();
        book.setTitle("Micronaut in Action");
        book.setYear(2025);
        book.setAuthor(savedAuthor);

        Book savedBook = bookRepo.save(book);
        assertNotNull(savedBook.getId());
        assertEquals("Micronaut in Action", savedBook.getTitle());
    }

    @Test
    void testFindBooksByAuthor() {
        Author author = new Author();
        author.setFirstName("Multi");
        author.setLastName("Writer");
        Author savedAuthor = authorRepo.save(author);

        Book book1 = new Book();
        book1.setTitle("Book One");
        book1.setYear(2024);
        book1.setAuthor(savedAuthor);

        Book book2 = new Book();
        book2.setTitle("Book Two");
        book2.setYear(2025);
        book2.setAuthor(savedAuthor);

        bookRepo.save(book1);
        bookRepo.save(book2);

        List<Book> books = bookRepo.findByAuthorId(savedAuthor.getId());
        assertEquals(2, books.size());
    }
}
