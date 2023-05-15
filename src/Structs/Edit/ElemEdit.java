package Structs.Edit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Structs.Elem;
import Structs.Elems; 


public class ElemEdit extends JComponent implements ActionListener {
    Elem element;
    Elems elements;
    Integer oldID;
    JInternalFrame frame;

    JTextField id_field;
    protected JTextField[] other_fields;

    public ElemEdit(Elem element, Elems elements){
        this.element = element;
        this.elements = elements;
        this.oldID = this.element.id;

        this.id_field = new JTextField(Integer.toString(this.element.id));
    }

    public void run(){
        frame = new JInternalFrame("Element Editor", true, true, true, true);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JButton id_reset = new JButton("ID reset");
        id_reset.addActionListener(this);
        id_reset.setActionCommand("id_reset");
        id_reset.setPreferredSize(new Dimension(0, 20));

        JButton save_button = new JButton("Save");
        save_button.addActionListener(this);
        save_button.setActionCommand("save");
        
        container.add(new JLabel("Element ID:"));
        container.add(this.id_field);
        container.add(id_reset);
        setOtherFieldsContainer(container);
        container.add(save_button);
    }

    protected void setOtherFieldsContainer(Container container){}

    public void actionPerformed(ActionEvent event){
        String cmd = event.getActionCommand();
        if(cmd.equals("save")){
            try{
                this.element.id = Integer.parseInt(this.id_field.getText());
                assignOtherFields();
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Id has to be integer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            if(this.element.checkData()){
                elements.replaceElem(this.oldID, this.element);
                frame.dispose();
            } else
                JOptionPane.showMessageDialog(null, "Invalid data!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(cmd.equals("id_reset")){
            this.element.idReset();
            elements.replaceElem(this.oldID, this.element);
            this.oldID = this.element.id;
            this.id_field.setText(Integer.toString(this.element.id));
        }
    }

    protected void assignOtherFields() throws NumberFormatException {}
}
