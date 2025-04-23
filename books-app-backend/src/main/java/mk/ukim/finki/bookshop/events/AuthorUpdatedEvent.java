package mk.ukim.finki.bookshop.events;

import lombok.Getter;
import mk.ukim.finki.bookshop.model.domain.Author;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AuthorUpdatedEvent extends ApplicationEvent {
    private LocalDateTime when;

    public AuthorUpdatedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public AuthorUpdatedEvent(Author source, LocalDateTime when) {
        super(source);
        this.when = when;
    }


}
