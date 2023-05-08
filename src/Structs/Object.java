package Structs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Object {
    int id;

    public Object(){ this(0); }
    public Object(int id){ this.id = id; }

    public String toString(){
        return "Object: " + this.id;
    }

    public void Edit(String file_name){
        // TODO: ObjectEditor editor = new ObjectEditor(this, file_name);
        // editor.run();
    }

    public static void save(Object object, String file){
        try{
            FileOutputStream fileOutput = new FileOutputStream(file);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(object);
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e){
            System.out.println("Couldn't save object!");
        }
    }

    public static Object read(String file){
        try {
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            Object object = (Object) objectInput.readObject();
            objectInput.close();
            fileInput.close();
            return object;
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist, created new object");
            return new Object();
        } catch (IOException e) {
            System.out.println("Error while reading data!");
        } catch (ClassNotFoundException e) {
            System.out.println("Object not found!");
        }

        return null;
    }
}
