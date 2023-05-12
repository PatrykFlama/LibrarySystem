
import Structs.Elems;
import Structs.Student;
import Structs.Students;
import Structs.Elements.Book;
import Structs.Elements.NewsPaper;
import Structs.Elements.Movie;

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
        for(int i = 0; i < 20; i++)
            s.addStudent(new Student("Some Student " + i, 1));

        Elems e = new Elems();
        e.addElem(new Book("Goood book", "Good author", 1900, 1));
        e.addElem(new Movie("Star Wars", "George Lucas", "sci-fi", 1977,  2));
        e.addElem(new Book("Solaris", "Stanislaw Lem", 1961, 3));
        e.addElem(new NewsPaper("Times", "12/34/5678", 4));
        for(int i = 0; i < 20; i++)
            e.addElem(new Book("Some Book " + i, "Some Author " + i, 1000 + i));

        Elems.save(e, "testluielements.txt");
        Students.save(s, "testluistudents.txt");

        LibrarianUI lUi = new LibrarianUI("testlui");
        lUi.run();
        while(lUi.isRunning()) {System.err.print("");}
    }
}

