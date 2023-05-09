package Structs.Elements;

import Structs.Elem;

public class Movie extends Elem {
    String title;
    String director;
    int year;
    String genre;

    public Movie(){ this("", ""); }
    public Movie(String title){ this(title, "", ("Movie"+title).hashCode()); }
    public Movie(String title, String genre){ this(title, genre, 0); }
    public Movie(String title, String genre, int id){ this(title, genre, id, ""); }
    public Movie(String title, String genre, int id, String director){ this(title, genre, id, director, 0); }
    public Movie(String title, String genre, int id, String director, int year){
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
