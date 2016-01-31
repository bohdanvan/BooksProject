package com.pa.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author apo
 */
@Controller
public class HomePageController {
    @RequestMapping("/")
    public ModelAndView redirectToBooks() {
        return new ModelAndView("redirect:/web/books");
    }
}
