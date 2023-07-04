package com.BasicSpringLibraryApp.Controller;

import com.BasicSpringLibraryApp.Repository.AuthorRepository;
import com.BasicSpringLibraryApp.Repository.BookRepository;
import com.BasicSpringLibraryApp.model.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String listAuthors(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors-list";
    }

    @GetMapping("/create")
    public String showCreateAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors-create";
    }

    @PostMapping
    public String createAuthor(@ModelAttribute("author") Author newAuthor) {
        authorRepository.save(newAuthor);
        return "redirect:/authors";
    }


    @GetMapping("/edit/{id}")
    public String showEditAuthorForm(@PathVariable("id") Long id, Model model) {
            Author author = authorRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Author not found with name: " + id));
            model.addAttribute("author", author);
            return "authors-update";
    }

    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable("id") Long id, @ModelAttribute("author") Author updateAuthor) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        existingAuthor.setName(updateAuthor.getName());
        authorRepository.save(existingAuthor);
        return "redirect:/authors";
    }


    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
            authorRepository.deleteById(id);
            return "redirect:/authors";
        }
}

