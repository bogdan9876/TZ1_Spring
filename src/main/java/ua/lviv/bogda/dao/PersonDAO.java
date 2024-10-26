package ua.lviv.bogda.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.bogda.models.Person;

import java.util.List;


@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("select p from Person p", Person.class)
                .getResultList();

        return people;
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class)
                .setParameter("id", id)
                .getSingleResult();

        return person;
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class)
                .setParameter("id", id)
                .getSingleResult();
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());

    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.createQuery("SELECT p FROM Person p WHERE p.id = :id", Person.class)
                .setParameter("id", id)
                .getSingleResult();

        session.remove(person);

    }
}
