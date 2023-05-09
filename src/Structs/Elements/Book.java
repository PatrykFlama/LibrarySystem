package Structs.Elements;

import Structs.Elem;

public class Book extends Elem {
    String name;
    String author;
    int year;

    public Book(){ this(""); }
    public Book(String name){ this(name, name.hashCode()); }
    public Book(String name, int id){ this(name, id, ""); }
    public Book(String name, int id, String author){ this(name, id, author, 0); }
    public Book(String name, int id, String author, int year){
        super(id);
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String toString(){
        return "Book: " + this.name + " by " + this.author + " (" + this.year + ")";
    }

    public void Edit(String file_name){
        // TODO: BookEdit editor = new BookEdit(this, file_name);
        // editor.run();
    }

    public static Book load(String file){
        return (Book) Elem.load(file);
    }
}
