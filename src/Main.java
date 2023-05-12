import Structs.Student;
import Structs.Students;

public class Main {
    // TODO: manage login and direct to lib/student UI
    public static void main(String[] args) {
        testLibrarianUI();
    }

    static void testLibrarianUI(){
        Students s = new Students();
        s.addStudent(new Student("Jan Kowalski", 3, 1));
        s.addStudent(new Student("Wieslaw Paleta", 0, 2));
        s.addStudent(new Student("Janusz Tracz", 1, 3));
        Students.save(s, "testlui.txt");

        LibrarianUI lUi = new LibrarianUI("testlui.txt");
        lUi.run();
        while(lUi.isRunning()) {System.err.print("");}
        lUi.kill();
    }
}

