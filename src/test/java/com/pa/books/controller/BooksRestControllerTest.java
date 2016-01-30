package com.pa.books.controller;

import com.pa.books.entity.Book;
import com.pa.books.persistence.BookDAO;
import com.pa.books.springconf.AppConfig;
import com.pa.books.springconf.WebAppConfig;
import junit.framework.TestCase;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, BooksRestControllerTest.TestConfig.class})
public class BooksRestControllerTest {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testFindAllBooks() throws Exception {
        when(bookDAO.findAllBooks()).thenReturn(mockBooks());

        mockMvc.perform(get("/rest/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].isbn", is("111111111")))
                .andExpect(jsonPath("$[0].author", is("John")))
                .andExpect(jsonPath("$[0].title", is("A")))
                .andExpect(jsonPath("$[1].isbn", is("222222222")))
                .andExpect(jsonPath("$[1].author", is("Bob")))
                .andExpect(jsonPath("$[1].title", is("B")));

        verify(bookDAO, times(1)).findAllBooks();
        verifyNoMoreInteractions(bookDAO);
    }


    private List<Book> mockBooks() {
        return Arrays.asList(
                new Book("111111111", "John", "A"),
                new Book("222222222", "Bob", "B")
        );
    }

    public void testSaveBook() throws Exception {

    }

    @Configuration
    public class TestConfig {

        @Bean
        public BookDAO bookDAO() {
            return Mockito.mock(BookDAO.class);
        }
    }
}