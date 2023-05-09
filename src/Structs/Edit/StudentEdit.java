package Structs.Edit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Structs.Student; 


public class StudentEdit extends JComponent implements ActionListener {
    Student obj;
    String file_name;

    public StudentEdit(Student obj, String file_name){
        this.obj = obj;
        this.file_name = file_name;
    }

    public void run(){
        JFrame frame = new JFrame("Student Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        
        Container content = frame.getContentPane()    
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel name_label = new JLabel("Name: ");
        JTextField name_field = new JTextField(this.obj.name);
        name_field.setMaximumSize(new Dimension(200, 20));
        content.add(name_label);
        content.add(name_field);
        
        JLabel year_label = new JLabel("Year: ");
        JTextField year_field = new JTextField(Integer.toString(this.obj.year));
        year_field.setMaximumSize(new Dimension(200, 20));
        content.add(year_label);
        content.add(year_field);

        JButton save_button = new JButton("Save");
        save_button.addActionListener(this);
        save_button.setActionCommand("save");
        content.add(save_button);
        
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        if(cmd.equals("save")){
            this.obj.name = e.getActionCommand();
            this.obj.year = Integer.parseInt(e.getActionCommand());
            Student.save(this.obj, this.file_name);
        }
    }


}
