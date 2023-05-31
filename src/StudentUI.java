import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.Map.Entry;

import javax.swing.*;

import Structs.Students;
import Structs.Elems;
import Structs.Student;

public class StudentUI extends JComponent implements ActionListener {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
    
    Student student;
    Students students;
    Elems elements;

    JInternalFrame frame;
    Container content;
    JDesktopPane desktop;

    public StudentUI(JDesktopPane desktop, int id, Students students, Elems elements){
        this.desktop = desktop;
        this.students = students;
        this.elements = elements;
        this.student = this.students.getStudent(id);
    }

    public void run(int dx, int dy){
        frame = new JInternalFrame("Student " + Integer.toString(student.id), true, true, true, true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // frame.setAutoscrolls(getAutoscrolls());
        frame.setSize(1000, 1000);
        
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

        JButton change_password = new JButton("Change password");
        change_password.addActionListener(this);
        change_password.setActionCommand("change_password");

        JButton save_and_exit = new JButton("Save and exit");
        save_and_exit.addActionListener(this);
        save_and_exit.setActionCommand("save_and_exit");
        
        content.add(list);
        content.add(rent);
        content.add(return_el);
        content.add(search);
        content.add(change_password);
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
                setDisplayBorrowedElementsContainer();
            } else if(cmd.equals("borrow_element")){
                setDisplayBorrowElementContainer();
            } else if(cmd.equals("return_element")){
                setDisplayReturnElementContainer();
            } else if(cmd.equals("search")){
                setDisplaySearchContainer();
            } else if(cmd.equals("change_password")){
                setDisplayChangePasswordContainer();
            }

            JButton back_button = new JButton("Back");
            back_button.addActionListener(this);
            back_button.setActionCommand("main_menu");
            content.add(back_button);
        }

        content.validate();
        content.repaint();
    }

    void setDisplayBorrowedElementsContainer(){
        JButton back_button = new JButton("Back");
        back_button.addActionListener(this);
        back_button.setActionCommand("main_menu");
        content.add(back_button);

        content.add(new JLabel("Borrowed elements:"));
        for(Entry<Integer, Integer> id_amt : student.borrowed_elems.entrySet()){
            try{
                content.add(new JTextField("Id " + id_amt.getKey().toString() + ", amt " + id_amt.getValue().toString() + ": " + elements.getElem(id_amt.getKey()).toString()));
            } catch(IllegalArgumentException e){
                continue;
            }
        }
    }

    void setDisplayBorrowElementContainer(){
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

    void setDisplayReturnElementContainer(){
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

    void setDisplaySearchContainer(){
        JLabel label = new JLabel("Enter searched name:");
        content.add(label);

        JTextField name = new JTextField();
        content.add(name);

        JTextArea result = new JTextArea();
        result.setEditable(false);
        content.add(result);

        JButton search_button = new JButton("Search");
        search_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String cmd = event.getActionCommand();
                if(cmd.equals("Search")){
                    String s = name.getText();
                    result.setText("");
                    for(Integer ids : elements.search(s)){
                        try{
                            result.append(elements.getElem(ids).toString() + "\n");
                        } catch(IllegalArgumentException e){
                            continue;
                        }
                    }
                }
            }
        });
        search_button.setActionCommand("Search");
        content.add(search_button);
    }

    void setDisplayChangePasswordContainer(){
        content.add(new JLabel("Enter old password:"));
        JTextField old_password = new JTextField();
        content.add(old_password);

        content.add(new JLabel("Enter new password:"));
        JTextField new_password = new JTextField();
        content.add(new_password);

        content.add(new JLabel("Enter new password again:"));
        JTextField new_password_check = new JTextField();
        content.add(new_password_check);

        JButton change_button = new JButton("Change");
        change_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String cmd = event.getActionCommand();
                if(cmd.equals("Change")){
                    if(!student.checkPassword(old_password.getText())){
                        JOptionPane.showMessageDialog(null, "Wrong password");
                        return;
                    }
                    if(!new_password.getText().equals(new_password_check.getText())){
                        JOptionPane.showMessageDialog(null, "Passwords don't match");
                        return;
                    }
                    student.changePassword(new_password.getText());
                    old_password.setText("");
                    new_password.setText("");
                    new_password_check.setText("");
                }
            }
        });
        change_button.setActionCommand("Change");
        content.add(change_button);
    }
}
