package main.sergeevVA.dao;

import main.sergeevVA.model.Book;
import main.sergeevVA.model.FindBook;

import java.util.List;

public interface BookDao {
    List<Book> findAll();

    Book findById(int id);

    void removeBookById(int id);

    void addBook(Book book);

    void updateBook(Book book);

    List<Book> findBooksByTemplate(FindBook template);

}
