package Structs.Elements;

import Structs.Elem;

public class NewsPaper extends Elem {
    String title;
    int date;

    public NewsPaper(){ this(""); }
    public NewsPaper(String title){ this(title, 0); }
    public NewsPaper(String title, int id){ this(title, id, 0); }
    public NewsPaper(String title, int id, int date){
        super(id);
        this.title = title;
        this.date = date;
    }

    public String toString(){
        return "NewsPaper: " + this.title + " (" + this.date + ")";
    }

    public void Edit(String file_name){
        // TODO: NewsPaperEditor editor = new NewsPaperEditor(this, file_name);
        // editor.run();
    }

    public static NewsPaper load(String file){
        return (NewsPaper) Elem.load(file);
    }
}
