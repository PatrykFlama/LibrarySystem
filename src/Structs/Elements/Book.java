package Structs.Elements;

import Structs.Elem;

public class Book extends Elem {
    public String name;
    public String author;
    public int year;

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
        return "Book: " + "(" + this.id + ") " + this.name + " by " + this.author + ", " + this.year;
    }

    public void idReset(){
        this.id = ("Book"+name).hashCode();
    }

    public static Book load(String file){
        return (Book) Elem.load(file);
    }

    public String getName(){
        return this.name;
    }
}
