package Structs.Elements;

import Structs.Elem;

public class Movie extends Elem {
    public String title;
    public String director;
    public int year;
    public String genre;

    public Movie(){ this(""); }
    public Movie(String title){ this(title, ""); }
    public Movie(String title, String director){ this(title, director, ""); }
    public Movie(String title, String director, String genre){ this(title, director, genre, 0); }
    public Movie(String title, String director, String genre, int year){ this(title, director, genre, year, ("Movie"+title).hashCode()); }
    public Movie(String title, String director, String genre, int year, int id){
        super(id);
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
    }

    public String toString(){
        return "Movie: " + "(" + this.id + ") " + this.title + " by " + this.director + ", " + this.year + " (" + this.genre + ")";
    }

    public void idReset(){
        this.id = ("Movie"+title).hashCode();
    }

    public static Movie load(String file){
        return (Movie) Elem.load(file);
    }
}
