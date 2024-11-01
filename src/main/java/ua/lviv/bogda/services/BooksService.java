package ua.lviv.bogda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.bogda.models.Book;
import ua.lviv.bogda.models.Person;
import ua.lviv.bogda.repositories.BookRepository;
import ua.lviv.bogda.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final PeopleRepository peopleRepository;

    private final BookRepository bookRepository;

    @Autowired
    public BooksService(PeopleRepository peopleRepository, BookRepository bookRepository) {
        this.peopleRepository = peopleRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public Page<Book> findAll(int page, int booksPerPage, boolean sortByYear) {
        Pageable pageable = sortByYear
                ? PageRequest.of(page, booksPerPage, Sort.by("year"))
                : PageRequest.of(page, booksPerPage);

        return bookRepository.findAll(pageable);
    }

    public Book findOne(int id) {
        Optional<Book> foundBooks = bookRepository.findById(id);
        return foundBooks.orElse(null);
    }

    public List<Book> findByNameStartingWith(String prefix) {
        return bookRepository.findByNameStartingWith(prefix);
    }

    public Optional<Person> getBookOwner(int id) {
        Optional<Book> book = bookRepository.findById(id);

        return book.map(Book::getOwner);
    }

    @Transactional
    public void release(int id) {
        Optional<Book> updatedBook = bookRepository.findById(id);
        if (updatedBook.isPresent()) {
            Book book = updatedBook.get();
            book.setOwner(null);
            bookRepository.save(book);
        }
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setOwner(selectedPerson);
            bookRepository.save(book);
        }
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
}
