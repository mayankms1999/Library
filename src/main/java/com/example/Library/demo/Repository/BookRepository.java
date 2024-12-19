package com.example.Library.demo.Repository;
import com.example.Library.demo.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByPublishedDateAfter(LocalDate date);
    List<Book> findByAuthorNameContainingIgnoreCase(String authorName);
}
