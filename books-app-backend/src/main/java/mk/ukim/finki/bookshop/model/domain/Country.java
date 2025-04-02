package mk.ukim.finki.bookshop.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String continent;

    @JsonIgnore
    @OneToMany(mappedBy = "country", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    List<Author> authorList;

    public Country() {}

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
        this.authorList = new ArrayList<>();
    }
}
