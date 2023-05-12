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
        setContainer(content);
        
        frame.pack();
        frame.setVisible(true);
    }

    void setContainer(Container container){
    //     id_field.setMaximumSize(new Dimension(200, 20));
    //     name_field.setMaximumSize(new Dimension(200, 20));
    //     term_field.setMaximumSize(new Dimension(200, 20));

        JButton edit_student = new JButton("Edit Student");
        edit_student.addActionListener(this);
        edit_student.setActionCommand("edit_student");
        
        JButton save_button = new JButton("Save");
        save_button.addActionListener(this);
        save_button.setActionCommand("save");
        
    //     container.add(new JLabel("Students ID:"));
    //     container.add(this.id_field);
    //     container.add(id_reset);
    //     container.add(new JLabel("Name:"));
    //     container.add(this.name_field);
    //     container.add(new JLabel("Term:"));
    //     container.add(this.term_field);

        container.add(save_button);
        container.add(edit_student);
    }

    public void actionPerformed(ActionEvent e){
    //     String cmd = e.getActionCommand();
    //     if(cmd.equals("save")){
    //         try {
    //             this.obj.id = Integer.parseInt(this.id_field.getText());
    //             this.obj.name = this.name_field.getText();
    //             this.obj.term = Integer.parseInt(this.term_field.getText());
    //         } catch (NumberFormatException except) {
    //             JOptionPane.showMessageDialog(null, "Id and term have to be integer!", "Error", JOptionPane.ERROR_MESSAGE);
    //         }
            
    //         if(this.obj.checkData())
    //             // Students.save(this.obj, this.file_name);
    //             setVisible(false);
    //         else
    //             JOptionPane.showMessageDialog(null, "Invalid data!", "Error", JOptionPane.ERROR_MESSAGE);
        // } else if(cmd.equals("id_reset")){
        //     this.obj.idReset();
        //     this.id_field.setText(Integer.toString(this.obj.id));
        // }
    }
    
    public Boolean isRunning(){
        return frame.isVisible();
    }

    // public void kill(){
    //     frame.dispose();
    // }
}
