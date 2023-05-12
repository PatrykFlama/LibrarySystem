import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;

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
    //     id_field.setMaximumSize(new Dimension(200, 20));
    //     name_field.setMaximumSize(new Dimension(200, 20));
    //     term_field.setMaximumSize(new Dimension(200, 20));
        
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

    }
    void setEditStudentContainer(Container container){
        
    }
    void setDisplayElementsContainer(Container container){

    }
    void setEditElementContainer(Container container){

    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        if(cmd.equals("display_students")){
            Container content = frame.getContentPane();
            content.removeAll();
            setDisplayStudentsContainer(content);
        } else if(cmd.equals("display_elements")){
            Container content = frame.getContentPane();
            content.removeAll();
            setDisplayElementsContainer(content);
        } else if(cmd.equals("edit_student")){
            Container content = frame.getContentPane();
            content.removeAll();
            setEditStudentContainer(content);
        } else if(cmd.equals("edit_element")){
            Container content = frame.getContentPane();
            content.removeAll();
            setEditElementContainer(content);
        } else if(cmd.equals("back")){
            Container content = frame.getContentPane();
            content.removeAll();
            setMainContainer(content);
        }
    }
    
    public Boolean isRunning(){
        return frame.isVisible();
    }

    public void kill(){
        frame.dispose();
    }
}
