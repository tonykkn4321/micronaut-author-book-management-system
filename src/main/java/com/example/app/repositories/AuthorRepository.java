package com.example.app.repositories;

import com.example.app.models.Author;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
