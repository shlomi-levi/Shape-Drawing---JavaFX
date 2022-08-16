
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.ArrayList;


public class applicationController {

    @FXML
    private Rectangle lineObj;

    @FXML
    private Circle circleObj;

    @FXML
    private Rectangle rectangleObj;

    @FXML
    private Pane paneObj;

    @FXML
    private ColorPicker colorPickerObj;

    @FXML
    void onDrag(MouseEvent event) { applicationHandler.handleDrag(event, circleObj, rectangleObj, lineObj); }

    @FXML
    void onDrop(MouseEvent event) { applicationHandler.handleDrop(event); }

    @FXML
    void onClear() { applicationHandler.handleClear(); }

    @FXML
    void onFullColorCheckBox() {
        applicationHandler.invertFullColor();
    }

    @FXML
    void onUndo() { applicationHandler.handleUndo(); }

    @FXML
    void onColorPick(ActionEvent event) {
        applicationHandler.setColorChosen(colorPickerObj.getValue());
    }

    @FXML
    void initialize() {
        paneObj.setClip(new Rectangle(paneObj.getPrefWidth(), paneObj.getPrefHeight()));
        applicationHandler.setOriginalElements(new ArrayList<>(paneObj.getChildren()));
        applicationHandler.setPaneObject(paneObj);
        colorPickerObj.setValue(Color.DARKSLATEBLUE);
        applicationHandler.setColorChosen(colorPickerObj.getValue());
    }

}
