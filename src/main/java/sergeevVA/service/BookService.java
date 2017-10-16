package main.java.sergeevVA.service;

import main.java.sergeevVA.dao.BookDao;
import main.java.sergeevVA.model.Book;
import main.java.sergeevVA.model.FindBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDaoImp;

    public List<Book>  getAllBooks(){
        return this.bookDaoImp.findAll();
    }

    public Book getBookById(int id){
        return this.bookDaoImp.findById(id);
    }

    public void removeBook(int id){
        this.bookDaoImp.removeBookById(id);
    }

    public void addBook(Book book){
        this.bookDaoImp.addBook(book);
    }

    public void updateBook(Book book){
        this.bookDaoImp.updateBook(book);
    }

    public List<Book> findBooksByTemplate(FindBook findBook){
        FindBook book = validateFindBook(findBook);

        return this.bookDaoImp.findBooksByTemplate(book);
    }

    private FindBook validateFindBook(FindBook findBook) {
        FindBook book = new FindBook();
        book.setAuthor(findBook.getAuthor());
        book.setTitle(findBook.getTitle());
        book.setReadAlready(findBook.getReadAlready());

        if (findBook.getIsbn() < 0) {
            book.setIsbn(0);
        }else{
            book.setIsbn(findBook.getIsbn());
        }

        if (findBook.getYearAfter() < 0){
            book.setYearAfter(0);
        }else {
            book.setYearAfter(findBook.getYearAfter());
        }

        if (findBook.getYearBefore() < 0){
            book.setYearBefore(0);
        }else{
            book.setYearBefore(findBook.getYearBefore());
        }


        return book;
    }


}
