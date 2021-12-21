package michaelrosenfeld.springframework.spring5webapp.bootstrap;

import michaelrosenfeld.springframework.spring5webapp.domain.Author;
import michaelrosenfeld.springframework.spring5webapp.domain.Book;
import michaelrosenfeld.springframework.spring5webapp.domain.Publisher;
import michaelrosenfeld.springframework.spring5webapp.repositories.AuthorRepository;
import michaelrosenfeld.springframework.spring5webapp.repositories.BookRepository;
import michaelrosenfeld.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Starting in Bootstrap...");

        Publisher ign = new Publisher("IGN", "Random street 22B", "Seattle",
                "Houston", "12345");

        publisherRepository.save(ign);

        Author michael = new Author("Michael", "Rosenfeld");
        Book myBook = new Book("Best Book", "21345231");
        michael.getBooks().add(myBook);
        myBook.setPublisher(ign);
        ign.getBooks().add(myBook);

        authorRepository.save(michael);
        bookRepository.save(myBook);

        Author joyce = new Author("Joyce", "Rosenfeld");
        Book joyceBook = new Book("Best Forks in Town", "23451121");
        joyce.getBooks().add(joyceBook);
        joyceBook.getAuthors().add(joyce);

        joyceBook.setPublisher(ign);
        ign.getBooks().add(joyceBook);

        authorRepository.save(joyce);
        bookRepository.save(joyceBook);



        System.out.println("Number of Books = " + bookRepository.count());
        System.out.println("Publishers = " + publisherRepository.count());
        System.out.println("IGN count of books = " + ign.getBooks().size());
   }
}
