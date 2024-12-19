package com.example.Library.demo.Service;

import com.example.Library.demo.Entity.Author;
import com.example.Library.demo.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Create a new author
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Get all authors
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Update an author
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            Author author = existingAuthor.get();
            author.setName(updatedAuthor.getName());
            return authorRepository.save(author);
        } else {
            throw new RuntimeException("Author not found with ID: " + id);
        }
    }

    // Find authors by name
    public List<Author> findAuthorsByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    // Delete an author
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}

