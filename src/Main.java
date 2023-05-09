import Structs.Student;

public class Main {
    // TODO: manage login and direct to lib/student UI
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Student s = new Student("Jan Kowalski", 1, 123321);
        s.Edit("test.txt");
    }
}

