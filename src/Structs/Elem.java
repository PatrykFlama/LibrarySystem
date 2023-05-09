package Structs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Elem implements Serializable {
    private static final long serialVersionUID = 1234L;
    int id;

    public Elem(){ this(0); }
    public Elem(int id){ this.id = id; }

    public String toString(){
        return "Object: " + this.id;
    }

    public void Edit(String file_name){
        // TODO: ElemEditor editor = new ElemEditor(this, file_name);
        // editor.run();
    }

    public static void save(Elem object, String file){
        try{
            FileOutputStream fileOutput = new FileOutputStream(file);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(object);
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e){
            System.err.println("Couldn't save element data!");
        }
    }

    public static Elem load(String file){
        try {
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            Elem object = (Elem) objectInput.readObject();
            objectInput.close();
            fileInput.close();
            return object;
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist, created new object");
            return new Elem();
        } catch (IOException e) {
            System.err.println("Error while reading data!");
        } catch (ClassNotFoundException e) {
            System.err.println("Object not found!");
        }

        return null;
    }
}
