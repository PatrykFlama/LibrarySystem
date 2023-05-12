package Structs.Edit.OtherElems;

import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Structs.Elems;
import Structs.Edit.ElemEdit;
import Structs.Elements.Movie;

public class EditMovie extends ElemEdit {
    Movie element;

    public EditMovie(Movie element, Elems elements){
        super(element, elements);
        this.element = element;
        this.other_fields = new JTextField[4];
        other_fields[0] = new JTextField(element.title);
        other_fields[1] = new JTextField(element.director);
        other_fields[2] = new JTextField(Integer.toString(element.year));
        other_fields[3] = new JTextField(element.genre);
    }

    protected void setOtherFieldsContainer(Container container){
        container.add(new JLabel("Movie title:"));
        container.add(this.other_fields[0]);
        container.add(new JLabel("Movie director:"));
        container.add(this.other_fields[1]);
        container.add(new JLabel("Movie year:"));
        container.add(this.other_fields[2]);
        container.add(new JLabel("Movie genre:"));
        container.add(this.other_fields[3]);
    }

    protected void assignOtherFields() throws NumberFormatException {
        element.title = this.other_fields[0].getText();
        element.director = this.other_fields[1].getText();
        element.year = Integer.parseInt(this.other_fields[2].getText());
        element.genre = this.other_fields[3].getText();
    }
}
