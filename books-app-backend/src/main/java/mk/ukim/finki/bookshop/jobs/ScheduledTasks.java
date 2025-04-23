package mk.ukim.finki.bookshop.jobs;

import mk.ukim.finki.bookshop.service.domain.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final BookService bookService;

    public ScheduledTasks(BookService bookService) {
        this.bookService = bookService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshBooksByAuthorView() {
        this.bookService.refreshBooksByAuthorView();
    }
}
