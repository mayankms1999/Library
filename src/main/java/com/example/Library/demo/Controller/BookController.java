package com.example.Library.demo.Controller;
import com.example.Library.demo.Entity.Book;
import com.example.Library.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Create a new book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // Get a list of all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Update an existing book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    // Find books by title
    @GetMapping("/search")
    public List<Book> findBooksByTitle(@RequestParam String title) {
        return bookService.findBooksByTitle(title);
    }

    // Find books published after a certain date
    @GetMapping("/publishedAfter")
    public List<Book> findBooksPublishedAfter(@RequestParam String date) {
        return bookService.findBooksPublishedAfter(LocalDate.parse(date));
    }

    // Find books by a specific author name
    @GetMapping("/author")
    public List<Book> findBooksByAuthorName(@RequestParam String authorName) {
        return bookService.findBooksByAuthorName(authorName);
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
