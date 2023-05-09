package Structs.Edit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Structs.Elem; 


public class ElemEdit extends JComponent implements ActionListener {
    Elem obj;
    String file_name;

    JTextField id_field;

    public ElemEdit(Elem obj, String file_name){
        this.obj = obj;
        this.file_name = file_name;

        this.id_field = new JTextField(Integer.toString(this.obj.id));
    }

    public void run(){
        JFrame frame = new JFrame("Elem Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setContainer(content);
        
        frame.pack();
        frame.setVisible(true);
    }

    void setContainer(Container container){
        id_field.setMaximumSize(new Dimension(200, 20));

        container.add(new JLabel("Elem ID:"));
        container.add(this.id_field);

        JButton save_button = new JButton("Save");
        save_button.addActionListener(this);
        save_button.setActionCommand("save");
        container.add(save_button);
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        if(cmd.equals("save")){
            this.obj.id = Integer.parseInt(this.id_field.getText());
            
            if(this.obj.checkData())
                Elem.save(this.obj, this.file_name);
            else
                JOptionPane.showMessageDialog(null, "Invalid data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
