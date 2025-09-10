package my.app.repositories;

import io.micronaut.data.repository.CrudRepository;
import my.app.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {}
