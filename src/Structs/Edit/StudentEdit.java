package Structs.Edit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Structs.Student; 


public class StudentEdit extends JComponent implements ActionListener {
    Student obj;
    JFrame frame;

    JTextField id_field;
    JTextField name_field;
    JTextField term_field;

    public StudentEdit(Student obj){
        this.obj = obj;

        this.id_field = new JTextField(Integer.toString(this.obj.id));
        this.name_field = new JTextField(this.obj.name);
        this.term_field = new JTextField(Integer.toString(this.obj.term));
    }

    public Student run(){
        frame = new JFrame("Student Editor");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setContainer(content);
        
        frame.pack();
        frame.setVisible(true);
        return this.obj;
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
                this.obj.id = Integer.parseInt(this.id_field.getText());
                this.obj.name = this.name_field.getText();
                this.obj.term = Integer.parseInt(this.term_field.getText());
            } catch (NumberFormatException except) {
                JOptionPane.showMessageDialog(null, "Id and term have to be integer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            if(this.obj.checkData())
                setVisible(false);
            else
                JOptionPane.showMessageDialog(null, "Invalid data!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(cmd.equals("id_reset")){
            this.obj.idReset();
            this.id_field.setText(Integer.toString(this.obj.id));
        }
    }
        
    public Boolean isRunning(){
            return frame.isVisible();
    }

    public void kill(){
        frame.dispose();
    }
}
