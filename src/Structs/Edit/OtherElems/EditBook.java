package Structs.Edit.OtherElems;

import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Structs.Elems;
import Structs.Edit.ElemEdit;
import Structs.Elements.Book;

public class EditBook extends ElemEdit {
    Book element;

    public EditBook(Book element, Elems elements){
        super(element, elements);
        this.element = element;
        this.other_fields = new JTextField[3];
        other_fields[0] = new JTextField(element.name);
        other_fields[1] = new JTextField(element.author);
        other_fields[2] = new JTextField(Integer.toString(element.year));
    }

    protected void setOtherFieldsContainer(Container container){
        container.add(new JLabel("Book name:"));
        container.add(this.other_fields[0]);
        container.add(new JLabel("Book author:"));
        container.add(this.other_fields[1]);
        container.add(new JLabel("Book year:"));
        container.add(this.other_fields[2]);
    }

    protected void assignOtherFields() throws NumberFormatException {
        element.name = this.other_fields[0].getText();
        element.author = this.other_fields[1].getText();
        element.year = Integer.parseInt(this.other_fields[2].getText());
    }
}
