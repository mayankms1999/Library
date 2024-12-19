package com.example.Library.demo.Service;
import com.example.Library.demo.Entity.Author;
import com.example.Library.demo.Entity.Book;
import com.example.Library.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Library.demo.Repository.AuthorRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // Create a new book
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Update a book
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setPublishedDate(updatedBook.getPublishedDate());
            if (updatedBook.getAuthor() != null) {
                Optional<Author> author = authorRepository.findById(updatedBook.getAuthor().getId());
                author.ifPresent(book::setAuthor);
            }
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with ID: " + id);
        }
    }

    // Find books by title
    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // Find books published after a certain date
    public List<Book> findBooksPublishedAfter(LocalDate date) {
        return bookRepository.findByPublishedDateAfter(date);
    }

    // Find books by author's name
    public List<Book> findBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(authorName);
    }

    // Delete a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
