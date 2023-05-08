package Structs;

public class Book {
    String name;
    String author;
    int year;
    int isbn;       // book id

    public Book(){ this(""); }
    public Book(String name){ this(name, 0); }
    public Book(String name, int isbn){ this(name, isbn, ""); }
    public Book(String name, int isbn, String author){ this(name, isbn, author, 0); }
    public Book(String name, int isbn, String author, int year){
        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.year = year;
    }
}
