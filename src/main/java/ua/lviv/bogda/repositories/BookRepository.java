package ua.lviv.bogda.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.bogda.models.Book;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

    List<Book> findByOwnerId(int ownerId);

    Page<Book> findAll(Pageable pageable);

}
