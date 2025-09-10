package my.app.models;

import io.micronaut.data.annotation.*;

@MappedEntity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Integer year;

    @MappedProperty("author_id")
    private Long authorId;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
}
