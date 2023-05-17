import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import javax.swing.*;

import Structs.Students;
import Structs.Elems;
import Structs.Student;

public class StudentUI extends JComponent implements ActionListener {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
    static final String elements_filename = "elements.txt";     //TODO escalate name

    Student student;
    Students students;
    Elems elements;

    JInternalFrame frame;
    Container content;
    JDesktopPane desktop;

    public StudentUI(JDesktopPane desktop, int id, Students students){
        this.desktop = desktop;
        this.students = students;
        this.student = this.students.getStudent(id);
        this.elements = Elems.load(elements_filename);
    }

    public void run(int dx, int dy){
        frame = new JInternalFrame("Student Editor", true, true, true, true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 500);
        
        content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        actionPerformed(new ActionEvent(this, 0, "main_menu")); // basically a hack to avoid code duplication, calls actionPerformed with a fake event

        frame.pack();
        frame.setLocation(dx, dy);
        frame.setVisible(true);
        desktop.add(frame);
        try{
            frame.setSelected(true);
        } catch(PropertyVetoException e){}
    }

    void setMainContainer(){
        JButton list = new JButton("List borrowed elements");
        list.addActionListener(this);
        list.setActionCommand("list_borrowed_elements");

        JButton rent = new JButton("Borrow element");
        rent.addActionListener(this);
        rent.setActionCommand("borrow_element");
        
        JButton return_el = new JButton("Return element");
        return_el.addActionListener(this);
        return_el.setActionCommand("return_element");
        
        JButton search = new JButton("Search for element");
        search.addActionListener(this);
        search.setActionCommand("search");

        JButton save_and_exit = new JButton("Save and exit");
        save_and_exit.addActionListener(this);
        save_and_exit.setActionCommand("save_and_exit");
        
        content.add(list);
        content.add(rent);
        content.add(return_el);
        content.add(search);
        content.add(save_and_exit);
    }

    public void actionPerformed(ActionEvent event){
        String cmd = event.getActionCommand();

        if(cmd.equals("main_menu")){
            content.removeAll();
            setMainContainer();
        } else if(cmd.equals("save_and_exit")){
            students.replaceStudent(student.id, student);
            frame.dispose();
        }
        else {
            content.removeAll();
            if(cmd.equals("list_borrowed_elements")){
                setDisplayBorrowedElementsContainer(); //todo
            } else if(cmd.equals("borrow_element")){
                setDisplayBorrowElementContainer(); //todo
            } else if(cmd.equals("return_element")){
                setDisplayReturnElementContainer(); //todo
            } else if(cmd.equals("search")){
                setDisplaySearchContainer(); //todo
            }

            JButton back_button = new JButton("Back");
            back_button.addActionListener(this);
            back_button.setActionCommand("main_menu");
            content.add(back_button);
        }

        content.validate();
        content.repaint();
    }

    void setDisplayBorrowedElementsContainer(){ //todo
        JButton back_button = new JButton("Back");
        back_button.addActionListener(this);
        back_button.setActionCommand("main_menu");
        content.add(back_button);

        content.add(new JLabel("Borrowed elements:"));
        for(int i = 0; i < student.borrowed_elems.size(); i++){
            JLabel element_label = new JLabel(elements.getElem(student.borrowed_elems.get(i)).toString());
            content.add(element_label);
        }
    }

    void setDisplayBorrowElementContainer(){ //todo
        JLabel label = new JLabel("Enter element ID:");
        content.add(label);

        JTextField element_id = new JTextField();
        content.add(element_id);

        JButton borrow_button = new JButton("Borrow");
        borrow_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String cmd = event.getActionCommand();
                if(cmd.equals("Borrow")){
                    int id = Integer.parseInt(element_id.getText());
                    student.borrowElement(id);
                    element_id.setText("");
                }
            }
        });
        borrow_button.setActionCommand("Borrow");
        content.add(borrow_button);
    }

    void setDisplayReturnElementContainer(){ //todo
        JLabel label = new JLabel("Enter element ID:");
        content.add(label);

        JTextField element_id = new JTextField();
        content.add(element_id);

        JButton return_button = new JButton("Return");
        return_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String cmd = event.getActionCommand();
                if(cmd.equals("Return")){
                    int id = Integer.parseInt(element_id.getText());
                    student.returnElement(id);
                    element_id.setText("");
                }
            }
        });
        return_button.setActionCommand("Return");
        content.add(return_button);
    }

    void setDisplaySearchContainer(){ //todo
        JLabel label = new JLabel("Enter element ID:");
        content.add(label);

        // JTextField element_id = new JTextField();
        // content.add(element_id);

        // JButton search_button = new JButton("Search");
        // search_button.addActionListener(new ActionListener(){
        //     public void actionPerformed(ActionEvent event){
        //         String cmd = event.getActionCommand();
        //         if(cmd.equals("Search")){
        //             int id = Integer.parseInt(element_id.getText());
        //             ElementUI element_ui = new ElementUI(desktop, id);
        //             element_ui.run(0, 0);
        //             element_id.setText("");
        //         }
        //     }
        // });
        // search_button.setActionCommand("Search");
        // content.add(search_button);
    }
}
