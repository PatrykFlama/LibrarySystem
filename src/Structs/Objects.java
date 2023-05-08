package Structs;
import java.util.HashMap;

public class Objects {
    HashMap<Integer, Object> books;

    public Objects(){
        this.books = new HashMap<Integer, Object>();
    }

    public void addBook(Book book){
        this.books.put(book.isbn, book);
    }

    public void removeBook(int book_id){
        this.books.remove(book_id);
    }

    // TODO saving and loading Objects
}
