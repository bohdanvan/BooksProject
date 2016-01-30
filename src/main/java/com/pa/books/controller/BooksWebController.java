package com.pa.books.controller;



import com.pa.books.persistence.BookDAO;
import com.pa.books.entity.Book;
import com.pa.books.persistence.exception.BookExistsPersistenceException;
import com.pa.books.persistence.exception.BookPersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.List;

/**
 * @author apo
 */
@Controller
@RequestMapping(value = "/web")
public class BooksWebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksWebController.class);

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView allBooks() {
        try {
            List<Book> books = bookDAO.findAllBooks();
            return new ModelAndView("books").addObject("books", books);
        } catch (BookPersistenceException e) {
            LOGGER.error("Exception while getting all books", e);
            throw new InternalServerException();
        }
    }


    @RequestMapping(value = "/book/save/form", method = RequestMethod.GET)
    public ModelAndView saveNewBook() {
        return new ModelAndView("saveBookForm").addObject(new Book());
    }

    @RequestMapping(value = "/book/save", method = RequestMethod.POST)
    public ModelAndView saveBook(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.info("Incorrect book parameters");
            return new ModelAndView("saveBookForm").addObject(bindingResult);
        }
        try {
            bookDAO.saveBook(book);

            return new ModelAndView("redirect:/web/books");

        }catch (BookExistsPersistenceException e){
            LOGGER.error("Book with isbn " + book.getIsbn() + " already exists", e);
            return new ModelAndView("saveBookForm")
                    .addObject(book)
                    .addObject("bookExistsError", true);
        }catch (BookPersistenceException e) {
            LOGGER.error("Exception while saving new book", e);
            throw new InternalServerException();
        }
    }

}


