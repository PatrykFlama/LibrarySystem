package Structs.Book;

public class Book extends Object {
    int id;
    String name;
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

    public String toString(){
        return "Book: " + this.name + " by " + this.author + " (" + this.year + ")";
    }

    public void Edit(String file_name){
        // TODO: BookEditor editor = new BookEditor(this, file_name);
        // editor.run();
    }

    public static Book read(String file){
        return (Book) Object.read(file);
    }
}
