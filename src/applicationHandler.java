import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.util.ArrayList;
import java.util.Collection;

public class applicationHandler {

    enum ShapesType {
        CIRCLE,
        RECTANGLE,
        LINE
    }

    private static Color colorChosen;
    private static Collection<Node> originalElements;
    private static applicationHandler.ShapesType shapeChosen;
    private static boolean isDragged = false, fullColor = false;
    private static Pane paneObject;

    static void setOriginalElements(ArrayList<Node> nodes) {
        originalElements = nodes;
    }

    static void setPaneObject(Pane paneObj) {
        paneObject = paneObj;
    }

    static void setColorChosen(Color c) {
        colorChosen = c;
    }

    static void invertFullColor() {
        fullColor = !fullColor;
    }

    static void handleDrag(MouseEvent event, Object circleObj, Object rectangleObj, Object lineObj) {
        if (isDragged)
            return;

        if (event.getSource() == circleObj)
            shapeChosen = ShapesType.CIRCLE;

        else if (event.getSource() == rectangleObj)
            shapeChosen = ShapesType.RECTANGLE;

        else
            shapeChosen = ShapesType.LINE;

        isDragged = true;
    }

    static void handleDrop(MouseEvent event) {
        isDragged = false;

        javafx.scene.shape.Shape newShape = null;

        double x = event.getSceneX(), y = event.getSceneY();

        switch(shapeChosen) {
            case CIRCLE: {
                final double CIRCLE_RADIUS = 75;
                newShape = new Circle( CIRCLE_RADIUS, fullColor ? colorChosen : Color.TRANSPARENT);
                ((Circle) (newShape)).setCenterX(x);
                ((Circle) (newShape)).setCenterY(y);
                break;
            }

            case RECTANGLE: {
                final double RECTANGLE_LENGTH = 125, RECTANGLE_WIDTH = 125;
                newShape = new Rectangle( RECTANGLE_WIDTH - 2, RECTANGLE_LENGTH - 2, fullColor ? colorChosen : Color.TRANSPARENT);
                ((Rectangle) (newShape)).setX(x - (RECTANGLE_WIDTH / 2));
                ((Rectangle) (newShape)).setY(y - (RECTANGLE_LENGTH / 2));
                break;
            }

            case LINE: {
                final double LINE_LENGTH = 150;
                newShape = new javafx.scene.shape.Line(x - (LINE_LENGTH / 2), y + (LINE_LENGTH / 2),
                        x + (LINE_LENGTH / 2), y - LINE_LENGTH / 2);
                break;
            }
        }

        newShape.setStroke(colorChosen);

        paneObject.getChildren().add(newShape);
    }

    static void handleUndo() {
        if(paneObject.getChildren().size() == originalElements.size()) // in that case, all the shapes have been removed.
            return;

        paneObject.getChildren().remove(paneObject.getChildren().size() - 1);
    }

    static void handleClear() {
        paneObject.getChildren().clear();
        paneObject.getChildren().addAll(originalElements);
    }
}
