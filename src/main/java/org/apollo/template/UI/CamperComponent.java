package org.apollo.template.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class CamperComponent extends VBox {

    private String LicensePlate = "AB101010";
    private int kmCount = 999;
    private int noRentals = 10;
    private float inSeson = 200020f;
    private float outSeson = 100030f;

    public CamperComponent() {

        setMaxHeight(100);
        setMaxWidth(100);

        // attaching css.
        setId("CamperComp");
        getStylesheets().add(("file:src/main/resources/org/apollo/template/css/CamperCompStyles.css"));

        // licence plate
        Label licencePlate = new Label(LicensePlate);
        licencePlate.setId("licencePlate");
        getChildren().add(licencePlate);

        // camperImage
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("file:src/main/resources/Designer3.jpeg"));
        imageView.setFitWidth(190);
        imageView.setFitHeight(190);

        getChildren().add(imageView);

        // Classification
        Label classificationLable = new Label("Classification");
        classificationLable.setId("ClassificationLabel");

        Label luxusLable = new Label("LUXUS");
        luxusLable.setId("luxusLable");
        luxusLable.setPrefWidth(100);
        luxusLable.setAlignment(Pos.CENTER);

        Label regular = new Label("REGULAR");
        regular.setId("regular");
        regular.setPrefWidth(100);
        regular.setAlignment(Pos.CENTER);

        Label basic = new Label("BASIC");
        basic.setId("basic");
        basic.setPrefWidth(100);
        basic.setAlignment(Pos.CENTER);

        HBox ClassificationHbox = new HBox();
        ClassificationHbox.setId("classificationHbox");

        // creating the classification vBox
        VBox vBoxClassification1 = new VBox();
        vBoxClassification1.setId("vboxClassification");

        VBox vBoxClassification2 = new VBox();
        vBoxClassification2.setId("vboxClassification");

        VBox vBoxClassification3 = new VBox();
        vBoxClassification3.setId("vboxClassification");

        vBoxClassification1.getChildren().add(luxusLable);
        vBoxClassification2.getChildren().add(regular);
        vBoxClassification3.getChildren().add(basic);


        ClassificationHbox.getChildren().addAll(vBoxClassification1, vBoxClassification2, vBoxClassification3);

        getChildren().addAll(classificationLable, ClassificationHbox);

        // specification
        Label specification = new Label("specification");
        specification.setId("specification");

        VBox specificationVbox = new VBox();
        specificationVbox.setId("specificationVbox");

        HBox specfificationHbox1 = new HBox();
        specfificationHbox1.setId("specificationHbox");

        Label specificationKmCount = new Label(kmCount + " KM");
        specificationKmCount.setId("alignedRight");
        specfificationHbox1.getChildren().addAll(new Label("Total kilometers | "),specificationKmCount );

        HBox specfificationHbox2 = new HBox();
        specfificationHbox2.setId("specificationHbox");

        Label specificationNoRentals = new Label(noRentals + "");
        specfificationHbox2.getChildren().addAll(new Label("Total No of rentals | "), specificationNoRentals);

        Label specificationInSeson = new Label(inSeson + " EURO");
        specificationInSeson.setId("alignedRight");
        HBox specfificationHbox3 = new HBox();

        specfificationHbox3.setId("specificationHbox");
        specfificationHbox3.getChildren().addAll(new Label("In season price | "), specificationInSeson);

        Label specificationOutSeson = new Label(outSeson + " EURO");
        specificationOutSeson.setId("alignedRight");
        HBox specfificationHbox4 = new HBox();

        specfificationHbox4.setId("specificationHbox");
        specfificationHbox4.getChildren().addAll(new Label("Out season price | "), specificationOutSeson);

        specificationVbox.getChildren().addAll(specfificationHbox1, specfificationHbox2, specfificationHbox3, specfificationHbox4);

        getChildren().add(specificationVbox);

        getChildren().add(new Button("More info"));



        setMargin(this, new Insets(50, 50, 50, 50));

    }
}
