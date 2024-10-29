package ua.lviv.bogda.services;

import org.springframework.beans.factory.annotation.Autowired;
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
public class PeopleService {

    private final PeopleRepository peopleRepository;

    private final BookRepository bookRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BookRepository bookRepository) {
        this.peopleRepository = peopleRepository;
        this.bookRepository = bookRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Optional<Person> findByName(String name) {
        Optional<Person> foundPerson = peopleRepository.findByName(name);
        return Optional.ofNullable(foundPerson.orElse(null));
    }

    public List<Book> getBooks(int ownerId) {
        return bookRepository.findByOwnerId(ownerId);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
