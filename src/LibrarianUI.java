import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import javax.swing.*;

import Structs.Student;
import Structs.Students;
import Structs.Elem;
import Structs.Elems;
import Structs.Edit.ElemEdit;
import Structs.Edit.OtherElems.*;
import Structs.Elements.Book;
import Structs.Elements.Movie;
import Structs.Elements.NewsPaper;
import Structs.Edit.StudentEdit; 


public class LibrarianUI extends JComponent implements ActionListener {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    Students students;
    Elems elements;

    JTextField currentStudentID;
    JTextField currentElementID;

    JInternalFrame frame;
    Container content;
    JDesktopPane desktop;

    public LibrarianUI(JDesktopPane desktop, Students students, Elems elements){
        this.desktop = desktop;

        this.students = students;
        this.elements = elements;

        currentStudentID = new JTextField();
        currentElementID = new JTextField();
    }

    public void run(int dx, int dy){
        frame = new JInternalFrame("Logged in as: Librarian", true, true, true, true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 500);
        
        content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        actionPerformed(new ActionEvent(this, 0, "main_menu")); // basically a hack to avoid code duplication, calls actionPerformed with a fake event
        
        frame.pack();
        repackFrame();
        frame.setLocation(dx, dy);
        frame.setVisible(true);
        desktop.add(frame);
        try{
            frame.setSelected(true);
        } catch(PropertyVetoException e){}
    }

    void repackFrame(){
        Dimension framesize = frame.getSize();
        if(framesize.getWidth() <= 200 && framesize.getHeight() <= 500){
            frame.pack();
            framesize = frame.getSize();
            frame.setSize(Math.min(200, (int)framesize.getWidth()), Math.min(500, (int)framesize.getHeight()));
        }
        content.validate();
        content.repaint();
    }

    void setMainContainer(){
        JButton display_students = new JButton("Display students");
        display_students.addActionListener(this);
        display_students.setActionCommand("display_students");

        JButton edit_student = new JButton("Edit Student");
        edit_student.addActionListener(this);
        edit_student.setActionCommand("edit_student");

        JButton add_student = new JButton("Add/remove Student");
        add_student.addActionListener(this);
        add_student.setActionCommand("add_student");
        
        JButton display_elements = new JButton("Display elements");
        display_elements.addActionListener(this);
        display_elements.setActionCommand("display_elements");
        
        JButton edit_element = new JButton("Edit element");
        edit_element.addActionListener(this);
        edit_element.setActionCommand("edit_element");
        
        JButton add_element = new JButton("Add/remove element");
        add_element.addActionListener(this);
        add_element.setActionCommand("add_element");

        JButton save_and_exit = new JButton("Save and exit");
        save_and_exit.addActionListener(this);
        save_and_exit.setActionCommand("save_and_exit");
        
        content.add(display_students);
        content.add(edit_student);
        content.add(add_student);
        content.add(display_elements);
        content.add(edit_element);
        content.add(add_element);
        content.add(save_and_exit);
    }

    void setDisplayStudentsContainer(){
        JButton back_button = new JButton("Back");
        back_button.addActionListener(this);
        back_button.setActionCommand("main_menu");
        
        content.add(back_button);
        JTextArea textArea = new JTextArea(students.toString());
        content.add(new JScrollPane(textArea));
    }
    void setEditStudentContainer(Boolean editor_mode){
        content.add(new JLabel("Student ID"));
        content.add(currentStudentID);
        if(editor_mode){
            JButton display_student = new JButton("Edit student");
            display_student.addActionListener(this);
            display_student.setActionCommand("student_edit_window");
            content.add(display_student);
        } else{
            content.add(new JLabel("Type in id to remove student"));
            content.add(new JLabel("Leave empty to add new student"));
            JButton add_student = new JButton("Execute");
            add_student.addActionListener(this);
            add_student.setActionCommand("add_student_action");
            content.add(add_student);
        }
    }
    void setDisplayElementsContainer(){
        JButton back_button = new JButton("Back");
        back_button.addActionListener(this);
        back_button.setActionCommand("main_menu");
        
        content.add(back_button);
        content.add(new JScrollPane(new JTextArea(elements.toString())));
    }
    void setEditElementContainer(Boolean editor_mode){
        content.add(new JLabel("Element ID"));
        content.add(currentElementID);
        if(editor_mode){
            JButton display_element = new JButton("Edit element");
            display_element.addActionListener(this);
            display_element.setActionCommand("element_edit_window");
            content.add(display_element);
        } else{
            content.add(new JLabel("Type in id to remove element"));
            content.add(new JLabel("Type book/movie/newspaper to add it"));
            JButton add_element = new JButton("Execute");
            add_element.addActionListener(this);
            add_element.setActionCommand("add_element_action");
            content.add(add_element);
        }
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

        } else if(cmd.equals("element_edit_window")){
            try{
                Integer elementID = Integer.parseInt(currentElementID.getText());
                Elem element = elements.getElem(elementID);

                Object editor;
                if(element instanceof Book)
                    editor = new EditBook((Book)element, this.elements);
                else if(element instanceof Movie)
                    editor = new EditMovie((Movie)element, this.elements);
                else if(element instanceof NewsPaper)
                    editor = new EditNP((NewsPaper)element, this.elements);
                else
                    editor = new ElemEdit(element, this.elements);

                ((ElemEdit) editor).run();
                JInternalFrame element_edit_frame = ((ElemEdit) editor).getFrame();
                setupNewFrame(element_edit_frame);
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id has to be integer!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch(IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Element with given id does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if(cmd.equals("save_and_exit")){
            frame.dispose();
        } else if(cmd.equals("add_student_action")){
            if(currentStudentID.getText().equals("")){  // no id - create student
                Student student = new Student();
                students.addStudent(student);
                JOptionPane.showMessageDialog(null, "Student has been added!", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                currentStudentID.setText(Integer.toString(student.id));
                actionPerformed(new ActionEvent(this, 0, "student_edit_window"));
                return;
            }
            try{
                Integer studentID = Integer.parseInt(currentStudentID.getText());
                Student student = students.getStudent(studentID);
                // student with given id already exists, remove him
                students.removeStudent(student.id);
                JOptionPane.showMessageDialog(null, "Student with given id has been removed!", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id has to be integer!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch(IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Student with given id does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(cmd.equals("add_element_action")){
            if(currentElementID.getText().equals("book")){  // no id - create element
                Elem element = new Book();
                elements.addElem(element);
                JOptionPane.showMessageDialog(null, "Element has been added!", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                currentElementID.setText(Integer.toString(element.id));
                actionPerformed(new ActionEvent(this, 0, "element_edit_window"));
                return;
            } else if(currentElementID.getText().equals("movie")){  // no id - create element
                Elem element = new Movie();
                elements.addElem(element);
                JOptionPane.showMessageDialog(null, "Element has been added!", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                currentElementID.setText(Integer.toString(element.id));
                actionPerformed(new ActionEvent(this, 0, "element_edit_window"));
                return;
            } else if(currentElementID.getText().equals("newspaper")){  // no id - create element
                Elem element = new NewsPaper();
                elements.addElem(element);
                JOptionPane.showMessageDialog(null, "Element has been added!", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
                currentElementID.setText(Integer.toString(element.id));
                actionPerformed(new ActionEvent(this, 0, "element_edit_window"));
                return;
            }
            try{
                Integer elementID = Integer.parseInt(currentElementID.getText());
                Elem element = elements.getElem(elementID);
                // element with given id already exists, remove him
                elements.removeElem(element.id);
                JOptionPane.showMessageDialog(null, "Element with given id has been removed!", "Operation successful", JOptionPane.INFORMATION_MESSAGE);
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Id has to be integer!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch(IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Element with given id does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            content.removeAll();
            if(cmd.equals("display_students")){
                setDisplayStudentsContainer();
            } else if(cmd.equals("display_elements")){
                setDisplayElementsContainer();
            } else if(cmd.equals("edit_student")){
                setEditStudentContainer(true);
            } else if(cmd.equals("add_student")){
                setEditStudentContainer(false);
            } else if(cmd.equals("edit_element")){
                setEditElementContainer(true);
            } else if(cmd.equals("add_element")){
                setEditElementContainer(false);
            }

            JButton back_button = new JButton("Back");
            back_button.addActionListener(this);
            back_button.setActionCommand("main_menu");
            content.add(back_button);
        }

        repackFrame();
    }

    void setupNewFrame(JInternalFrame frame){
        openFrameCount = (openFrameCount + 1) % 10;
        frame.setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
        desktop.add(frame);
        try{
            frame.setSelected(true);
        } catch(PropertyVetoException e){}
    }
}
