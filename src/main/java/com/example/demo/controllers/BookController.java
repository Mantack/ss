package com.example.demo.controllers;

import com.example.demo.repos.Book;
import com.example.demo.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("")
    public String main(Map<String, Object> model) {
        model.put("book", bookRepo.findAll());
        return "upd";
    }



    @PostMapping("add")
    public String add(@RequestParam String title, @RequestParam String author, Map<String, Object> model) {
        Book book1 = new Book();
        book1.setTitle(title);
        book1.setAuthor(author);
        bookRepo.save(book1);
        model.put("book", bookRepo.findAll());
        return "upd";
    }

    @GetMapping("update")
    public String update(@RequestParam Long id, Map<String, Object> model) {
        Book book = bookRepo.findById(id).get();

        model.put("id",id);
        model.put("title",book.getTitle());
        model.put("author",book.getAuthor());

        bookRepo.save(book);
        model.put("book", bookRepo.findAll());
        return "update";
    }

    @PostMapping("update")
    public String update(@RequestParam Long id, @RequestParam String title, @RequestParam String author, Map<String, Object> model) {
        Book book = bookRepo.findById(id).get();
        book.setTitle(title);
        book.setAuthor(author);
        bookRepo.save(book);
        model.put("book", bookRepo.findAll());
              return "book";
    }

    @GetMapping("delete")
    public String delete(@RequestParam Long id, Map<String, Object> model) {
        bookRepo.deleteById(id);
        model.put("book", bookRepo.findAll());
        return "book";
    }

    @PostMapping("delete2")
    public String delete2(@RequestParam Long id, Map<String, Object> model) {
        bookRepo.deleteById(id);
        model.put("book", bookRepo.findAll());
        return "book";
    }
//    @PostMapping("delete")
//    public String delete( Map<String, Object> model){
//        model.put("book",bookRepo.findAll());
//        return "book";
//    }

}
