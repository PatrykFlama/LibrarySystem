import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;

import javax.swing.*;

import Structs.Student;
import Structs.Students;
import Structs.Elems;
import Structs.Edit.StudentEdit; 


public class LibrarianUI extends JComponent implements ActionListener {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    String filename;
    Students students;
    Elems elements;

    JTextField currentStudentID;
    JTextField currentElementID;

    JInternalFrame frame;
    Container content;
    JFrame mainFrame;
    JDesktopPane desktop;

    public LibrarianUI(String filename){
        this.students = Students.load(filename+"students.txt");
        this.elements = Elems.load(filename+"elements.txt");
        this.filename = filename;

        currentStudentID = new JTextField();
        currentElementID = new JTextField();

        mainFrame = new JFrame("Library Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        desktop = new JDesktopPane();
        mainFrame.setContentPane(desktop);
        mainFrame.setSize(500, 500);
        mainFrame.setVisible(true);
    }

    public void run(){
        frame = new JInternalFrame("Logged in as: Librarian", true, true, true, true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 500);
        
        content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        actionPerformed(new ActionEvent(this, 0, "main_menu"));
        
        frame.pack();
        frame.setVisible(true);

        desktop.add(frame);
    }

    void setMainContainer(){
        JButton display_students = new JButton("Display students");
        display_students.addActionListener(this);
        display_students.setActionCommand("display_students");

        JButton edit_student = new JButton("Edit Student");
        edit_student.addActionListener(this);
        edit_student.setActionCommand("edit_student");
        
        JButton display_elements = new JButton("Display elements");
        display_elements.addActionListener(this);
        display_elements.setActionCommand("display_elements");
        
        JButton edit_element = new JButton("Edit element");
        edit_element.addActionListener(this);
        edit_element.setActionCommand("edit_element");

        JButton save_and_exit = new JButton("Save and exit");
        save_and_exit.addActionListener(this);
        save_and_exit.setActionCommand("save_and_exit");
        
        content.add(display_students);
        content.add(edit_student);
        content.add(display_elements);
        content.add(edit_element);
        content.add(save_and_exit);
    }

    void setDisplayStudentsContainer(){
        // TODO some kind of scroll bar
        JButton back_button = new JButton("Back");
        back_button.addActionListener(this);
        back_button.setActionCommand("main_menu");
        
        content.add(back_button);
        content.add(new JTextArea(students.toString()));
    }
    void setEditStudentContainer(){
        content.add(new JLabel("Student ID"));
        content.add(currentStudentID);
        JButton display_student = new JButton("Edit student");
        display_student.addActionListener(this);
        display_student.setActionCommand("student_edit_window");
        content.add(display_student);
    }
    void setDisplayElementsContainer(){
        // TODO some kind of scroll bar
        JButton back_button = new JButton("Back");
        back_button.addActionListener(this);
        back_button.setActionCommand("main_menu");
        
        content.add(back_button);
        content.add(new JTextArea(elements.toString()));
    }
    void setEditElementContainer(){
        content.add(new JLabel("Element ID"));
        content.add(currentElementID);
        JButton display_element = new JButton("Edit element");
        display_element.addActionListener(this);
        display_element.setActionCommand("element_edit_window");
        content.add(display_element);
    }

    public void actionPerformed(ActionEvent event){
        String cmd = event.getActionCommand();

        if(cmd.equals("main_menu")){
            content.removeAll();
            setMainContainer();
        } else if(cmd.equals("student_edit_window")){
            try{
                Integer studentID = Integer.parseInt(currentStudentID.getText());
                Student student = students.getStudent(studentID);

                StudentEdit editor = new StudentEdit(student, this.students);
                editor.run();
                JInternalFrame student_edit_frame = editor.getFrame();
                setupNewFrame(student_edit_frame);
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id has to be integer!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch(IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Student with given id does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if(cmd.equals("save_and_exit")){
            Students.save(students, this.filename);
            this.kill();
        }
        else {
            Container content = frame.getContentPane();
            content.removeAll();
            if(cmd.equals("display_students")){
                setDisplayStudentsContainer();
            } else if(cmd.equals("display_elements")){
                setDisplayElementsContainer();
            } else if(cmd.equals("edit_student")){
                setEditStudentContainer();
            } else if(cmd.equals("edit_element")){
                setEditElementContainer();
            }

            JButton back_button = new JButton("Back");
            back_button.addActionListener(this);
            back_button.setActionCommand("main_menu");
            content.add(back_button);
        }

        content.validate();
        content.repaint();
    }

    void setupNewFrame(JInternalFrame frame){
        openFrameCount = (openFrameCount + 1) % 10;
        frame.setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
        desktop.add(frame);
        try{
            frame.setSelected(true);
        } catch(PropertyVetoException e){}
    }
    
    public Boolean isRunning(){
        return frame.isVisible();
    }

    public void kill(){
        frame.dispose();
    }
}
