package mk.ukim.finki.bookshop.events;

import lombok.Getter;
import mk.ukim.finki.bookshop.model.domain.Author;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class AuthorCreatedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public AuthorCreatedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public AuthorCreatedEvent(Author source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
