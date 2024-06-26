package org.apollo.template.UI;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class EmailTextField extends TextField {

    /**
     * A TextField that has access to a method which can validate if
     * the given text in a TextField has the form of an email address
     */
    public EmailTextField(){
        super();
    }

    /**
     * Method for validating the given email, with regex
     * @return Returns an observable boolean.
     */
    public ObservableValue<? extends Boolean> emailVerificationProperty(){

        SimpleBooleanProperty isValidEmail = new SimpleBooleanProperty();
        isValidEmail.set(false);
        //If statement for checking if the given text is an email address
        if(this.getText().matches("[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,4}")){
            isValidEmail.set(true);
        }

        return isValidEmail;
    }

}
