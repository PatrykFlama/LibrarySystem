package Structs.Elements;

import Structs.Elem;

public class Book extends Elem {
    String name;
    String author;
    int year;

    public Book(){ this(""); }
    public Book(String name){ this(name, ""); }
    public Book(String name, String author){ this(name, author, 0); }
    public Book(String name, String author, int year){ this(name, author, year, ("Book"+name).hashCode()); }
    public Book(String name, String author, int year, int id){
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
