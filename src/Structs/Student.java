package Structs;
import java.util.HashMap;

public class Student {
    int id;
    String name;
    int year;
    HashMap<Integer, Integer> borrowed_books; // book id, copies rented

    public Student(){ this("", 1); }
    public Student(String name){ this(name, 1); }
    public Student(String name, int year){ this(name, year, name.hashCode()); }
    public Student(String name, int year, int id){
        this.id = id;
        this.name = name;
        this.year = year;
        this.borrowed_books = new HashMap<Integer, Integer>();
    }

    public void borrowBook(int book_id){
        Integer book_name = this.borrowed_books.get(book_id);
        if(book_name == null){
            this.borrowed_books.put(book_id, 1);
        } else {
            this.borrowed_books.put(book_id, book_name + 1);
        }
    }

    public void returnBook(int book_id) throws Exception {
        Integer book_name = this.borrowed_books.get(book_id);
        if(book_name == null){
            throw new Exception("You have not rented this book.");
        } else {
            if(book_name == 1){
                this.borrowed_books.remove(book_id);
            } else {
                this.borrowed_books.put(book_id, book_name - 1);
            }
        }
    }

    // TODO saving and loading student accounts
}