package my.app.repositories;

import io.micronaut.data.repository.CrudRepository;
import my.app.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {}
