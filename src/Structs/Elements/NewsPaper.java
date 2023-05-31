package Structs.Elements;

import Structs.Elem;

public class NewsPaper extends Elem {
    public String title;
    public String date;

    public NewsPaper(){ this(""); }
    public NewsPaper(String title){ this(title, ""); }
    public NewsPaper(String title, String date){ this(title, date, ("NewsPaper"+title).hashCode()); }
    public NewsPaper(String title, String date, int id){
        super(id);
        this.title = title;
        this.date = date;
    }

    public String toString(){
        return "NewsPaper: " + "(" + this.id + ") " + this.title + ", " + this.date;
    }

    public void idReset(){
        this.id = ("NewsPaper"+title).hashCode();
    }

    public String getSearchString(){
        return "newspaper" + this.title + this.date;
    }

    public static NewsPaper load(String file){
        return (NewsPaper) Elem.load(file);
    }
}
