package Structs;
import java.util.HashMap;

public class Elems {
    HashMap<Integer, Elem> elems;

    public Elems(){
        this.elems = new HashMap<Integer, Elem>();
    }

    public void addElem(Elem elem){
        this.elems.put(elem.id, elem);
    }

    public void removeBook(int book_id){
        this.elems.remove(book_id);
    }

    // TODO saving and loading Objects
}
