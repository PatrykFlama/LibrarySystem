import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Structs.Students; 


public class LibrarianUI extends JComponent implements ActionListener {
    Students obj;
    JFrame frame;

    JTextField currentStudentID;

    public LibrarianUI(String filename){
        this.obj = Students.load(filename);
    }

    public void run(){
        frame = new JFrame("Logged in as: Librarian");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setMainContainer(content);
        
        frame.pack();
        frame.setVisible(true);
    }

    void setMainContainer(Container container){
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
        
        container.add(display_students);
        container.add(edit_student);
        container.add(display_elements);
        container.add(edit_element);
    }

    void setDisplayStudentsContainer(Container container){
        container.add(new JTextArea("dis stud"));
    }
    void setEditStudentContainer(Container container){
        container.add(new JTextArea("ed stud"));
        
    }
    void setDisplayElementsContainer(Container container){
        container.add(new JTextArea("dis el"));
        
    }
    void setEditElementContainer(Container container){
        container.add(new JTextArea("ed el"));

    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        if(cmd.equals("display_students")){
            Container content = frame.getContentPane();
            content.removeAll();
            setDisplayStudentsContainer(content);
        } else if(cmd.equals("back")){
            Container content = frame.getContentPane();
            content.removeAll();
            setMainContainer(content);
        } else {
            Container content = frame.getContentPane();
            content.removeAll();
            if(cmd.equals("display_elements")){
                setDisplayElementsContainer(content);
            } else if(cmd.equals("edit_student")){
                setEditStudentContainer(content);
            } else if(cmd.equals("edit_element")){
                setEditElementContainer(content);
            }

            JButton back_button = new JButton("Back");
            back_button.addActionListener(this);
            back_button.setActionCommand("back");
            content.add(back_button);
        }
    }
    
    public Boolean isRunning(){
        return frame.isVisible();
    }

    public void kill(){
        frame.dispose();
    }
}
