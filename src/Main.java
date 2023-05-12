import Structs.Student;

public class Main {
    // TODO: manage login and direct to lib/student UI
    public static void main(String[] args) {
        testLibrarianUI();
    }

    static void testStudent(){
        Student s = Student.load("test.txt");
        s.borrowElem(1);
        s.borrowElem(1);
        s.borrowElem(1);
        s.borrowElem(2);
        s.Edit();
        Student.save(s, "test.txt");
        System.err.println(s);
    }

    static void testLibrarianUI(){
        LibrarianUI lUi = new LibrarianUI("testlui.txt");
        lUi.run();
        while(lUi.isRunning()) {System.err.print("");}
        lUi.kill();
    }
}

