import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Structs.Students; 


public class LibrarianUI extends JComponent implements ActionListener {
    String filename;
    Students students;
    JFrame frame;
    Container content;

    JTextField currentStudentID;

    public LibrarianUI(String filename){
        this.students = Students.load(filename);
        this.filename = filename;

        currentStudentID = new JTextField();
    }

    public void run(){
        frame = new JFrame("Logged in as: Librarian");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        
        content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        actionPerformed(new ActionEvent(this, 0, "main_menu"));
        
        frame.setVisible(true);
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
        content.add(new JTextArea("dis stud"));
    }
    void setEditStudentContainer(){
        content.add(new JTextArea("Student ID"));
        content.add(currentStudentID);
        JButton display_student = new JButton("Edit student");
        display_student.addActionListener(this);
        display_student.setActionCommand("student_edit_window");
        content.add(display_student);
    }
    void setDisplayElementsContainer(){
        content.add(new JTextArea("dis el"));
    }
    void setEditElementContainer(){
        content.add(new JTextArea("ed el"));
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();

        if(cmd.equals("main_menu")){
            content.removeAll();
            setMainContainer();
        } else if(cmd.equals("student_edit_window")){
            try{
                students.editStudent(Integer.parseInt(currentStudentID.getText()));
            } catch(NumberFormatException except) {
                JOptionPane.showMessageDialog(null, "Id has to be integer!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch(IllegalArgumentException except) {
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
        frame.pack();
    }
    
    public Boolean isRunning(){
        return frame.isVisible();
    }

    public void kill(){
        frame.dispose();
    }
}
