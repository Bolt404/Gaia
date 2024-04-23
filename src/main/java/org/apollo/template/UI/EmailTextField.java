package org.apollo.template.UI;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class EmailTextField extends TextField {

    public EmailTextField(){
        super();
    }

    public ObservableValue<? extends Boolean> emailVerificationProperty(){

        SimpleBooleanProperty isValidEmail = new SimpleBooleanProperty();
        isValidEmail.set(false);

        if(this.getText().matches("[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,4}")){
            isValidEmail.set(true);
        }

        return isValidEmail;
    }

}
