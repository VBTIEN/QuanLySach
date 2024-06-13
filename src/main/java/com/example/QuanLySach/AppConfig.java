package com.example.QuanLySach;

import com.example.QuanLySach.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public List<Book> getBooks()
    {
        List<Book> listBooks = new ArrayList<>();
        Book book3 = Book.builder()
                .id(3)
                .title("Effective Java")
                .author("Joshua Bloch")
                .price(45.0)
                .category("Programming")
                .build();
        listBooks.add(book3);
        return listBooks;
    }

}
