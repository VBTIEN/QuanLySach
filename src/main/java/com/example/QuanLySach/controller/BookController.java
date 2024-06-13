package com.example.QuanLySach.controller;

import com.example.QuanLySach.model.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private List<Book> listBook;

    @GetMapping("/")
    public String showAllBooks(Model model){
        model.addAttribute("listBook",listBook);
        model.addAttribute("title","Danh sách cuốn sách");

        return "book/list";
    }

    /*Thêm sách*/
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model){
        if(result.hasErrors()){
            return "book/add";
        }
        listBook.add(book);
        return "redirect:/";
    }

    /*Sửa sách*/
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") long id, Model model ){
        Optional<Book> editBook = listBook.stream().filter(p->p.getId()==id).findFirst();
        if(editBook.isPresent()){
            model.addAttribute("book", editBook.get());
            return "book/edit";
        } else {
            return "not-found";
        }
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book updateBook){
        listBook.stream().filter(p->p.getId()==updateBook.getId()).findFirst().ifPresent(p-> listBook.set(listBook.indexOf(p),updateBook));
        return "redirect:/";
    }

    /*Xóa sách*/
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        listBook.removeIf(p->p.getId()==id);
        return "redirect:/";
    }

}
