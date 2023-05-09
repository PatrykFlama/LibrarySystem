package Structs;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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

    public Student getStudent(int student_id){
        return this.students.get(student_id);
    }

    public void editStudent(int student_id, String file_name){
        Student student = this.students.get(student_id);
        student.Edit(file_name);
    }

    public String toString(){
        String result = "";
        for (Student student : this.students.values()){
            result += student.toString() + "\n";
        }
        return result;
    }

    public static void save(Students object, String file){
        Students.save(object, file);
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
