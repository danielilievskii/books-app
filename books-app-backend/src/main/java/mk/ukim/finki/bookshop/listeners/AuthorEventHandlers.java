package mk.ukim.finki.bookshop.listeners;

import mk.ukim.finki.bookshop.events.AuthorCreatedEvent;
import mk.ukim.finki.bookshop.events.AuthorDeletedEvent;
import mk.ukim.finki.bookshop.events.AuthorUpdatedEvent;
import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.service.domain.AuthorService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventHandlers {

    private final AuthorService authorService;

    public AuthorEventHandlers(AuthorService authorService) {
        this.authorService = authorService;
    }

    @EventListener
    public void onAuthorCreated(AuthorCreatedEvent event) {
        this.authorService.refreshAuthorsPerCountryView();
    }

    @EventListener
    public void onAuthorUpdated(AuthorUpdatedEvent event) {
        this.authorService.refreshAuthorsPerCountryView();
    }

    @EventListener
    public void onAuthorDeleted(AuthorDeletedEvent event) {
        this.authorService.refreshAuthorsPerCountryView();
    }
}
