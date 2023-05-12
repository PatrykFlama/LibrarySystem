package Structs;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Students implements Serializable{
    HashMap<Integer, Student> students;

    public Students(){
        this.students = new HashMap<Integer, Student>();
    }

    public void addStudent(Student student){
        this.students.put(student.id, student);
    }

    public void removeStudent(int student_id){
        this.students.remove(student_id);
    }

    public Student getStudent(int student_id) throws IllegalArgumentException {
        if(!this.students.containsKey(student_id)) throw new IllegalArgumentException("Student with id " + student_id + " does not exist");
        return this.students.get(student_id);
    }

    public void editStudent(int student_id){
        Student student = this.getStudent(student_id);
        student.Edit();
        if(student != null) this.students.put(student_id, student);
    }

    public String toString(){
        String result = "";
        for (Student student : this.students.values()){
            result += student.toString() + "\n";
        }
        return result;
    }

    public static void save(Students object, String file){
        try{
            FileOutputStream fileOutput = new FileOutputStream(file);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(object);
            objectOutput.close();
            fileOutput.close();
        } catch (IOException e){
            System.err.println("Couldn't save students accounts!");
        }
    }

    public static Students load(String file){
        try {
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            Students object = (Students) objectInput.readObject();
            objectInput.close();
            fileInput.close();
            return object;
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist, created new object");
            return new Students();
        } catch (IOException e) {
            System.err.println("Error while reading data!");
        } catch (ClassNotFoundException e) {
            System.err.println("Object not found!");
        }

        return null;
    }
}
