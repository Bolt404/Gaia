package org.apollo.template.UI;

import javafx.scene.layout.AnchorPane;

public class CamperComponent extends AnchorPane {
    private int maxCHeight;
    private int minCHeight;
    private int minCWidth;
    private int maxCWidth;

    public CamperComponent() {
        setStyle("-fx-background-color: lightblue;");
        setMaxCWidth(150);
        setMaxCHeight(150);

        setMinCHeight(150);
        setMinCWidth(150);
    }

    public int getMaxCHeight() {
        return maxCHeight;
    }

    public void setMaxCHeight(int maxHeight) {
        this.maxCHeight = maxHeight;
        setMaxHeight(maxHeight);
    }

    public int getMinCHeight() {
        return minCHeight;
    }

    public void setMinCHeight(int minHeight) {
        this.minCHeight = minHeight;
        setMinHeight(minHeight);
    }

    public int getMinCWidth() {
        return minCWidth;
    }

    public void setMinCWidth(int minWidth) {
        this.minCWidth = minWidth;
        setMinWidth(minWidth);
    }

    public int getMaxCWidth() {
        return maxCWidth;
    }

    public void setMaxCWidth(int maxWidth) {
        this.maxCWidth = maxWidth;
        setMaxWidth(maxWidth);
    }
}
