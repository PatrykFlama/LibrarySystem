package Structs;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;


public class Student implements Serializable {
    private static final long serialVersionUID = 1234L;
    public int id;
    public String name;
    public int term;
    HashMap<Integer, Integer> borrowed_elems; // id, amt

    public Student(){ this("None"); }
    public Student(String name){ this(name, 0); }
    public Student(String name, int term){ this(name, term, ("Student"+name).hashCode()); }
    public Student(String name, int term, int id){
        this.id = id;
        this.name = name;
        this.term = term;
        this.borrowed_elems = new HashMap<Integer, Integer>();
    }

    public void borrowElem(int id){
        Integer book_name = this.borrowed_elems.get(id);
        if(book_name == null){
            this.borrowed_elems.put(id, 1);
        } else {
            this.borrowed_elems.put(id, book_name + 1);
        }
    }

    public void returnElem(int id) throws Exception {
        Integer book_name = this.borrowed_elems.get(id);
        if(book_name == null){
            throw new Exception("You have not rented this book.");
        } else {
            if(book_name == 1){
                this.borrowed_elems.remove(id);
            } else {
                this.borrowed_elems.put(id, book_name - 1);
            }
        }
    }

    public String toString(){
        String result = "(" + this.id + ") " + this.name + ", term " + this.term;
        for (Integer id : this.borrowed_elems.keySet()){
            result += "    " + id + ": " + this.borrowed_elems.get(id) + "\n";
        }
        return result;
    }

    public Boolean checkData(){     // true if data is correct
        return !(this.name.equals("") || this.term < 0);
    }

    public void idReset(){
        this.id = ("Student"+name).hashCode();
    }

    public static void save(Student object, String file){
        try{
            FileOutputStream fileOutput = new FileOutputStream(file);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(object);
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e){
            System.err.println("Couldn't save student account!");
        }
    }

    public static Student load(String file){
        try {
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            Student object = (Student) objectInput.readObject();
            objectInput.close();
            fileInput.close();
            return object;
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist, created new object");
            return new Student();
        } catch (IOException e) {
            System.err.println("Error while reading data!");
        } catch (ClassNotFoundException e) {
            System.err.println("Object not found!");
        }

        return null;
    }
}