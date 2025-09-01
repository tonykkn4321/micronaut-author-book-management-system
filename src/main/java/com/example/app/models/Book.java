package com.example.app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // Getters and setters
}
