package org.apollo.template.Service;

import javafx.beans.binding.Bindings;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TextToImageBind {

    private static final Image SUCCESS = new Image("file:src/main/resources/org/apollo/template/images/sucess.png");
    private static final Image ERROR = new Image("file:src/main/resources/org/apollo/template/images/error.png");

    public static void bindTextToImage(TextField textField, ImageView imageView) {
        imageView.imageProperty().bind(Bindings.when(
                textField.textProperty().length().greaterThan(0)
        ).then(SUCCESS).otherwise(ERROR));
    }
}
