package Structs.Elements;

import Structs.Elem;

public class Movie extends Elem {
    String title;
    String director;
    int year;
    String genre;

    public Movie(){ this(""); }
    public Movie(String title){ this(title, ""); }
    public Movie(String title, String genre){ this(title, genre, ""); }
    public Movie(String title, String genre, String director){ this(title, genre, director, 0); }
    public Movie(String title, String genre, String director, int year){ this(title, genre, director, year, ("Movie"+title).hashCode()); }
    public Movie(String title, String genre, String director, int year, int id){
        super(id);
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
    }

    public String toString(){
        return "Movie: " + this.title + " by " + this.director + " (" + this.year + ") " + this.genre;
    }
        
    public void Edit(String file_name){
        // TODO: MovieEdit editor = new MovieEdit(this, file_name);
        // editor.run();
    }

    public static Movie load(String file){
        return (Movie) Elem.load(file);
    }
}
