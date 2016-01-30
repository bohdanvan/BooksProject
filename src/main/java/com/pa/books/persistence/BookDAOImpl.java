package com.pa.books.persistence;


import com.pa.books.entity.Book;
import com.pa.books.persistence.exception.BookExistsPersistenceException;
import com.pa.books.persistence.exception.BookPersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author apo
 */
@Repository
public class BookDAOImpl implements BookDAO {

    private static final String queryFindAllBooks = "SELECT b FROM Book b";

    private static final Logger LOGGER = LoggerFactory.getLogger(BookDAO.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Book> findAllBooks() throws BookPersistenceException {
        Query query = entityManager.createQuery(queryFindAllBooks, Book.class);

        try {
            return (List<Book>) query.getResultList();
        } catch (Exception e) {
            throw new BookPersistenceException("Exception while getting books from storage", e);
        }
    }

    @Override
    public void saveBook(Book book) throws BookPersistenceException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
        } catch (EntityExistsException e) {
            entityManager.getTransaction().rollback();
            throw new BookExistsPersistenceException("Book with isbn " + book.getIsbn() + " already exists", e);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new BookPersistenceException("Exception while persisting book", e);
        }
    }
}
