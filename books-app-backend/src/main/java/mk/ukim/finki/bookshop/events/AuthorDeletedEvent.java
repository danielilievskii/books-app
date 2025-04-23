package mk.ukim.finki.bookshop.events;

import lombok.Getter;
import mk.ukim.finki.bookshop.model.domain.Author;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class AuthorDeletedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public AuthorDeletedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public AuthorDeletedEvent(Author source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
