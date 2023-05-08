package Structs;
import java.util.HashMap;

public class Books {
    HashMap<Integer, Book> books;   // book isbn (id), book

    public Books(){
        this.books = new HashMap<Integer, Book>();
    }

    public void addBook(Book book){
        this.books.put(book.isbn, book);
    }

    public void removeBook(int book_id){
        this.books.remove(book_id);
    }

    // TODO saving and loading books
}
