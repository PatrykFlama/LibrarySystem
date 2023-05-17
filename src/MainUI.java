import java.awt.Container;
import java.awt.event.*;
import java.beans.PropertyVetoException;

import javax.swing.*;
import Structs.Elems;
import Structs.Student;
import Structs.Students;
import Structs.Elements.Book;
import Structs.Elements.NewsPaper;
import Structs.Elements.Movie;

public class MainUI implements ActionListener {
    static int openFrameCount = 0;
    static final int xOffset = 150, yOffset = 5;
    static final String students_filename = "students.txt";

    Students students;

    JFrame mainFrame;
    JDesktopPane desktop;

    JInternalFrame loginFrame;
    Container loginContent;

    JTextField librarianLogin;
    JTextField librarianPasswd;
    JTextField studentLogin;
    JTextField studentPasswd;

    public MainUI(){
        librarianLogin = new JTextField();
        librarianPasswd = new JTextField();
        studentLogin = new JTextField();
        studentPasswd = new JTextField();

        students = Students.load(students_filename);
    }
    
    public void run() {
        regenTestData();
        setupDesktop();
        setupMainFrame();
    }
    
    void LibrarianUI(){
        LibrarianUI lUi = new LibrarianUI(this.desktop, students);
        lUi.run(xOffset, yOffset);
    }
    
    void StudentUI(int id){
        StudentUI sUi = new StudentUI(this.desktop, id, students);
        sUi.run(xOffset, yOffset);
    }

    void setupDesktop(){
        mainFrame = new JFrame("Library Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        desktop = new JDesktopPane();
        mainFrame.setContentPane(desktop);
        mainFrame.setSize(500, 500);
        mainFrame.setVisible(true);
    }

    void setupMainFrame(){
        loginFrame = new JInternalFrame("Login", true, true, true, true);
        loginFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        loginFrame.setSize(200, 200);
        loginContent = loginFrame.getContentPane();
        loginContent.setLayout(new BoxLayout(loginContent, BoxLayout.Y_AXIS));

        displayLogin();

        loginFrame.setVisible(true);
        desktop.add(loginFrame);
    }

    void displayLogin(){
        loginContent.removeAll();

        JButton libButton = new JButton("Login as Librarian");
        libButton.addActionListener(this);
        libButton.setActionCommand("librarian_login_screen");
        loginContent.add(libButton);

        JButton studButton = new JButton("Login as Student");
        studButton.addActionListener(this);
        studButton.setActionCommand("student_login_screen");
        loginContent.add(studButton);

        JButton save_and_exit = new JButton("Save and exit");
        save_and_exit.addActionListener(this);
        save_and_exit.setActionCommand("save_and_exit");
        loginContent.add(save_and_exit);

        loginFrame.pack();
    }

    void displayLoginLibrarian(){
        loginContent.add(new JLabel("Login:"));
        loginContent.add(librarianLogin);
        
        loginContent.add(new JLabel("Password:"));
        loginContent.add(librarianPasswd);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setActionCommand("login_librarian");
        loginContent.add(loginButton);
    }
    
    void displayLoginStudent(){
        loginContent.add(new JLabel("Login:"));
        loginContent.add(studentLogin);

        loginContent.add(new JLabel("Password:"));
        loginContent.add(studentPasswd);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setActionCommand("login_student");
        loginContent.add(loginButton);
    }

    Boolean checkLibrarianCredentials(){
        return true;    //todo
    }
    Boolean checkStudentCredentials(){
        return true;    //todo
    }

    public void actionPerformed(ActionEvent event){
        String cmd = event.getActionCommand();

        if(cmd == "login_screen"){
            displayLogin();
        } else if(cmd == "login_librarian"){
            //todo
            if(checkLibrarianCredentials()) LibrarianUI();
        } else if(cmd == "login_student"){
            //todo
            if(checkLibrarianCredentials()) StudentUI(1);
        } else if(cmd == "save_and_exit"){
            Students.save(students, students_filename);
            System.exit(0);
        } else{
            loginContent.removeAll();
            if(cmd == "librarian_login_screen"){
                displayLoginLibrarian();
            } else if(cmd == "student_login_screen"){
                displayLoginStudent();
            }

            JButton backButton = new JButton("Back");
            backButton.addActionListener(this);
            backButton.setActionCommand("login_screen");
            loginContent.add(backButton);

            loginFrame.pack();
        }
    }

    void setupNewFrame(JInternalFrame frame){
        openFrameCount = (openFrameCount + 1) % 6;
        frame.setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
        desktop.add(frame);
        try{
            frame.setSelected(true);
        } catch(PropertyVetoException e){}
    }
    
    void regenTestData(){
        Students s = new Students();
        Student test = new Student("Jan Kowalski", 3, 1);
        test.borrowElement(1);
        test.borrowElement(2);
        test.borrowElement(3);
        s.addStudent(test);
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

        Elems.save(e, "elements.txt");
        Students.save(s, "students.txt");
    }
}
