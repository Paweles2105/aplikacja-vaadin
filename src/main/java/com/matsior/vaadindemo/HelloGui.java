package com.matsior.vaadindemo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("hello")
public class HelloGui extends VerticalLayout {

    public  HelloGui() {
        TextField textField = new TextField("Podaj imię");
        Button button = new Button("Left", new Icon(VaadinIcon.ARROW_LEFT));
        Label label = new Label();

        button.addClickListener(clickEvent -> {
            label.setText("Hello " + textField.getValue());
        });

        add(textField, button, label);

    }


}
