package com.BasicSpringLibraryApp.Controller;

import com.BasicSpringLibraryApp.Repository.AuthorRepository;
import com.BasicSpringLibraryApp.Repository.BookRepository;
import com.BasicSpringLibraryApp.model.Author;
import com.BasicSpringLibraryApp.model.Book;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books-list";
    }

    @GetMapping("/create")
    public String showCreateBookForm(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);
        return "books-create";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") Book newBook) {
        bookRepository.save(newBook);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "books-update";
    }


    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book updatedBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        Author author = authorRepository.findById(updatedBook.getAuthor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + updatedBook.getAuthor().getId()));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(author);

        bookRepository.save(book);
        return "redirect:/books";
    }



    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
           bookRepository.deleteById(id);
            return "redirect:/books";
        }
    }
