package my.app.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import my.app.models.Book;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId); // ← This is the missing method
}
