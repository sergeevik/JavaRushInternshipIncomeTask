package main.java.sergeevVA.controller;

import main.java.sergeevVA.model.Book;
import main.java.sergeevVA.model.FindBook;
import main.java.sergeevVA.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getAllBooks(@RequestParam(required = false) Integer page){

        List<Book> books = bookService.getAllBooks();
        PagedListHolder<Book> bookPagedListHolder = new PagedListHolder<>(books);
        bookPagedListHolder.setPageSize(10);
        if (page == null || page < 1 ||  page > bookPagedListHolder.getPageCount() ){
            page = 1;
        }

        bookPagedListHolder.setPage(page - 1);

        ModelAndView modelAndView = new ModelAndView("allBookPage");
        modelAndView.addObject("books", bookPagedListHolder.getPageList());
        modelAndView.addObject("page", page);
        modelAndView.addObject("maxPages", bookPagedListHolder.getPageCount());

        return modelAndView;
    }



    @RequestMapping(value = "/infoBook/{id}", method = RequestMethod.GET)
    public ModelAndView infoBook(@PathVariable("id") int id ){
        Book bookById = this.bookService.getBookById(id);
        ModelAndView modelAndView = new ModelAndView("infoBookPage");
        modelAndView.addObject("book", bookById);
        return modelAndView;
    }



    @RequestMapping(value = "/findBookView", method = RequestMethod.GET)
    public ModelAndView findBookView(){
        ModelAndView view = new ModelAndView("findBookQueryPage");
        view.addObject("findBook", new FindBook());
        return view;
    }

    @RequestMapping(value = "/findBookList", method = RequestMethod.POST)
    public ModelAndView findBookList(@ModelAttribute("findBook") FindBook findBook){
        List<Book> books = this.bookService.findBooksByTemplate(findBook);
        ModelAndView view = new ModelAndView("findBookListPages");
        view.addObject("books", books);
        return view;
    }




    @RequestMapping(value = "/editBook/{id}", method = RequestMethod.GET)
    public ModelAndView editBookView(@PathVariable("id") int id ){
        Book bookById = this.bookService.getBookById(id);
        ModelAndView modelAndView = new ModelAndView("editBookPage");
        modelAndView.addObject("book", bookById);
        return modelAndView;
    }

    @RequestMapping(value = "/editBookLogic", method = RequestMethod.POST)
    public String editBookLogic(@ModelAttribute("book") Book book){
        Book bookById = this.bookService.getBookById(book.getId());

        if (!bookById.equals(book)){
            book.setReadAlready(false);
            this.bookService.updateBook(book);
        }else if (!bookById.isReadAlready() && book.isReadAlready()){
            this.bookService.updateBook(book);
        }

        return "redirect:/books/list";
    }



    @RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") int id, @RequestParam(required = false) int page ){
        this.bookService.removeBook(id);
        if (page == 0){
            page = 1;
        }
        return "redirect:/books/list?page=" + page;
    }



    //Добавление книги
    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public ModelAndView addBookView(){
        ModelAndView view = new ModelAndView("addBookPage");
        view.addObject("book", new Book());
        return view;
    }

    @RequestMapping(value = "/addBookLogic", method = RequestMethod.POST)
    public String addBookLogic(@ModelAttribute("book") Book book){
        this.bookService.addBook(book);
        return "redirect:/books/list";
    }
}
