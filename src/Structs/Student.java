package Structs;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class Student implements Serializable {
    private static final long serialVersionUID = 1234L;
    public int id;
    public String name;
    public int term;
    public HashMap<Integer, ArrayList<Date>> borrowed_elems; // id, time(date)
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private String password;

    public Student(){ this("None"); }
    public Student(String name){ this(name, 0); }
    public Student(String name, int term){ this(name, term, ("Student"+name).hashCode()); }
    public Student(String name, int term, int id){
        this.id = id;
        this.name = name;
        this.term = term;
        this.borrowed_elems = new HashMap<Integer, ArrayList<Date>>();
        this.password = "";
    }

    public String toString(){  
        String result = "(" + this.id + ") " + this.name + ", term " + this.term;
        return result + elementsToString();
    }

    public String elementsToString(){
        String result = "";
        for (Integer id : this.borrowed_elems.keySet()){
            for(Date time : this.borrowed_elems.get(id)){
                result += "\n    " + id + ": " + formatter.format(time);
            }
        }
        return result;
    }

    public ArrayList<Integer> getIds(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Integer id : this.borrowed_elems.keySet()){
            result.add(id);
        }
        return result;
    }

    public ArrayList<String> getBorrowDates(int elem_id){
        ArrayList<String> result = new ArrayList<String>();
        for(Date time : this.borrowed_elems.get(elem_id)){
            result.add(formatter.format(time));
        }
        return result;
    }

    public void borrowElement(int elem_id){
        if(this.borrowed_elems.containsKey(elem_id)){
            this.borrowed_elems.get(elem_id).add(new Date(System.currentTimeMillis()));
            this.borrowed_elems.put(elem_id, this.borrowed_elems.get(elem_id));
        } else {
            ArrayList<Date> new_list = new ArrayList<Date>();
            new_list.add(new Date(System.currentTimeMillis()));
            this.borrowed_elems.put(elem_id, new_list);
        }
    }

    public void returnElement(int elem_id){
        if(this.borrowed_elems.containsKey(elem_id)){
            if(this.borrowed_elems.get(elem_id).size() > 1){
                this.borrowed_elems.get(elem_id).remove(0);
                this.borrowed_elems.put(elem_id, this.borrowed_elems.get(elem_id));
            } else {
                this.borrowed_elems.remove(elem_id);
            }
        }
    }

    public Boolean checkData(){     // true if data is correct
        return !(this.name.equals("") || this.term < 0);
    }

    public void idReset(){
        this.id = ("Student"+name).hashCode();
    }

    public void changePassword(String new_password){
        this.password = new_password;
    }
    public Boolean checkPassword(String password){
        return this.password.equals(password);
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