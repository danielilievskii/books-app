package mk.ukim.finki.bookshop.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Immutable
@Subselect("SELECT * FROM public.authors_per_country")
public class AuthorsPerCountryView {

    @Id
    @Column(name = "country_id")
    Long countryId;

    @Column(name = "num_authors")
    Integer numAuthors;
}
