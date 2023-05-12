package Structs.Edit.OtherElems;

import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Structs.Elems;
import Structs.Edit.ElemEdit;
import Structs.Elements.NewsPaper;

public class EditNP extends ElemEdit {
    NewsPaper element;

    public EditNP(NewsPaper element, Elems elements){
        super(element, elements);
        this.element = element;
        this.other_fields = new JTextField[2];
        other_fields[0] = new JTextField(element.title);
        other_fields[1] = new JTextField(element.date);
    }

    protected void setOtherFieldsContainer(Container container){
        container.add(new JLabel("NewsPaper title:"));
        container.add(this.other_fields[0]);
        container.add(new JLabel("NewsPaper date:"));
        container.add(this.other_fields[1]);
    }

    protected void assignOtherFields() throws NumberFormatException {
        element.title = this.other_fields[0].getText();
        element.date = this.other_fields[1].getText();
    }
}
