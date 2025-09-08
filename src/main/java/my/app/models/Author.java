package my.app.models;

import io.micronaut.data.annotation.*;
import java.util.List;

@MappedEntity
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    // JDBC doesn't support @OneToMany directly
    // You can model this as a manual join in your repository
    // or use @Relation if you're using Micronaut Data 4+

    // Example placeholder field
    // private List<Book> books;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
