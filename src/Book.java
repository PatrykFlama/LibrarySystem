public class Book {
    String name;
    int id;
    String author;
    int year;

    public Book(){ this(""); }
    public Book(String name){ this(name, 0); }
    public Book(String name, int id){ this(name, id, ""); }
    public Book(String name, int id, String author){ this(name, id, author, 0); }
    public Book(String name, int id, String author, int year){
        this.name = name;
        this.id = id;
        this.author = author;
        this.year = year;
    }
}
