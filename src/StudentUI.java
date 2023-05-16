import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import javax.swing.*;

import Structs.Students;
import Structs.Student;

public class StudentUI extends JComponent implements ActionListener {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
    static final String students_filename = "students.txt";     //TODO escalate name

    Student student;

    JInternalFrame frame;
    Container content;
    JDesktopPane desktop;

    public StudentUI(JDesktopPane desktop, int id){
        this.desktop = desktop;
        this.student = (Students.load(students_filename)).getStudent(id);
    }

    public void run(int dx, int dy){
        frame = new JInternalFrame("Student Editor");
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

    }

    public void actionPerformed(ActionEvent event){
        String cmd = event.getActionCommand();

        if(cmd.equals("main_menu")){
            content.removeAll();
            setMainContainer();
        }
    }
}
