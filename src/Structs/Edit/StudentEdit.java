package Structs.Edit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Structs.Student;
import Structs.Students;


public class StudentEdit extends JComponent implements ActionListener {
    Student student;
    Students students;
    Integer oldID;
    JInternalFrame frame;

    JTextField id_field;
    JTextField name_field;
    JTextField term_field;

    public StudentEdit(Student student, Students students){
        this.student = student;
        this.students = students;
        this.oldID = this.student.id;

        this.id_field = new JTextField(Integer.toString(this.student.id));
        this.name_field = new JTextField(this.student.name);
        this.term_field = new JTextField(Integer.toString(this.student.term));
    }

    public void run(){
        frame = new JInternalFrame("Student Editor", true, true, true, true);
        // frame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 500);
        
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setContainer(content);
        
        frame.pack();
        frame.setVisible(true);
    }

    public JInternalFrame getFrame(){
        return this.frame;
    }

    void setContainer(Container container){
        id_field.setMaximumSize(new Dimension(200, 20));
        name_field.setMaximumSize(new Dimension(200, 20));
        term_field.setMaximumSize(new Dimension(200, 20));

        JButton id_reset = new JButton("ID reset");
        id_reset.addActionListener(this);
        id_reset.setActionCommand("id_reset");
        id_reset.setPreferredSize(new Dimension(0, 20));
        
        JButton save_button = new JButton("Save");
        save_button.addActionListener(this);
        save_button.setActionCommand("save");
        
        container.add(new JLabel("Student ID:"));
        container.add(this.id_field);
        container.add(id_reset);
        container.add(new JLabel("Name:"));
        container.add(this.name_field);
        container.add(new JLabel("Term:"));
        container.add(this.term_field);

        container.add(save_button);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        if(cmd.equals("save")){
            try {
                this.student.id = Integer.parseInt(this.id_field.getText());
                this.student.name = this.name_field.getText();
                this.student.term = Integer.parseInt(this.term_field.getText());
            } catch (NumberFormatException except) {
                JOptionPane.showMessageDialog(null, "Id and term have to be integer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            if(this.student.checkData()){
                students.replaceStudent(oldID, student);
                this.kill();
            } else
                JOptionPane.showMessageDialog(null, "Invalid data!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(cmd.equals("id_reset")){
            this.student.idReset();
            this.id_field.setText(Integer.toString(this.student.id));
        }
    }
        
    public Boolean isRunning(){
        return frame.isVisible();
    }

    public void kill(){
        frame.dispose();
    }
}
