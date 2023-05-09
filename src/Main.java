import Structs.Student;

public class Main {
    // TODO: manage login and direct to lib/student UI
    public static void main(String[] args) {
        System.err.println("Hello world!");
        Student s = Student.load("test.txt");
        s.Edit("test.txt");
    }
}

