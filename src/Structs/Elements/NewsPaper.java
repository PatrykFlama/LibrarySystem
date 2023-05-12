package Structs.Elements;

import Structs.Elem;

public class NewsPaper extends Elem {
    String title;
    int date;

    public NewsPaper(){ this(""); }
    public NewsPaper(String title){ this(title, 0); }
    public NewsPaper(String title, int date){ this(title, date, ("NewsPaper"+title).hashCode()); }
    public NewsPaper(String title, int date, int id){
        super(id);
        this.title = title;
        this.date = date;
    }

    public String toString(){
        return "NewsPaper: " + this.title + " (" + this.date + ")";
    }

    public void Edit(String file_name){
        // TODO: NewsPaperEdit editor = new NewsPaperEdit(this, file_name);
        // editor.run();
    }

    public static NewsPaper load(String file){
        return (NewsPaper) Elem.load(file);
    }
}
