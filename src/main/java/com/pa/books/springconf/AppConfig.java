package com.pa.books.springconf;

import com.pa.books.persistence.BookDAO;
import com.pa.books.persistence.BookDAOImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author apo
 */
@Configuration
@ComponentScan(basePackages = "com.pa.books")
public class AppConfig {

    @Bean
    public EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BooksJPA");
        return emf.createEntityManager();
    }

}
