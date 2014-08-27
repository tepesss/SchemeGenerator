package controller.GraphicUtils.ConnectionStrategy;

import controller.GraphicUtils.ConnectionsDrawer;
import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 8/18/14.
 */
public class StraightStrategy implements PathDrawingStrategy{
    private List<Line2D> linesList = new ArrayList<>();

    @Override
    public List<Line2D> getLinesList(Point2D start, Point2D end, GraphicsContext gc) {
        linesList.add(new Line2D.Double(start, end));
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
        return linesList;
    }

    @Override
    public List<Line2D> getLinesListWithShifts(Point2D start, Point2D end, GraphicsContext gc, List<Line2D> list) {
        applyShift(start, end, list);
        linesList.add(new Line2D.Double(start, end));
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
        return getLinesList(start, end, gc);
    }

    private void applyShift(Point2D p1, Point2D p2, List<Line2D> list){
        Line2D line2Dp1 = ConnectionsDrawer.linesContainPoint(p1, list);
        Line2D line2Dp2 = ConnectionsDrawer.linesContainPoint(p2, list);
        if (line2Dp1 == null && line2Dp2 ==null){
            return;
        }

            p1.setLocation(p1.getX(), p1.getY()-5);
            p2.setLocation(p2.getX(), p2.getY() - 5);

        applyShift(p1, p2, list);
    }
}
