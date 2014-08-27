package controller.GraphicUtils.ConnectionStrategy;

import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 8/18/14.
 */
public interface PathDrawingStrategy {
    abstract List<Line2D> getLinesList(Point2D start, Point2D end, GraphicsContext gc);

    List<Line2D> getLinesListWithShifts(Point2D start, Point2D end, GraphicsContext gc, List<Line2D> list);
}
