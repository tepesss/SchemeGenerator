package controller.GraphicUtils.ConnectionStrategy;

import controller.GraphicUtils.ConnectionsDrawer;
import controller.utils.CommonValues;
import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 8/27/14.
 */
public class RetrospectionStrategy implements PathDrawingStrategy {
    public static int X_SHIFT = 15;
    public static int Y_SHIFT = 10;
    protected List<Line2D> linesList = new ArrayList<>();
    protected List<Line2D> list = new ArrayList<>();
    GraphicsContext gc;

    @Override
    public List<Line2D> getLinesList(Point2D start, Point2D end, GraphicsContext gc) {
        this.gc = gc;
        Line2D line = new Line2D.Double(start.getX(), start.getY(), end.getX(), end.getY());
        divideLine(line);
        drawAllLines();
        restoreShift();
        return linesList;
    }

    @Override
    public List<Line2D> getLinesListWithShifts(Point2D start, Point2D end, GraphicsContext gc, List<Line2D> list) {
        checkAndShift(start, list);
        checkAndShift(end, list);
        return  getLinesList(start, end, gc);
    }

    private void divideLine(Line2D line){
        double p_y = CommonValues.LOGICAL_ELEMENT_HEIGHT/2 + Y_SHIFT;
        double p_x = CommonValues.LOGICAL_ELEMENT_WIDTH/2 + X_SHIFT;
        Point2D p2 = new Point2D.Double(line.getX1()+ X_SHIFT, line.getY1());
        checkAndShift(p2, list);
        Point2D p3 = new Point2D.Double(p2.getX(), line.getY2() + p_y);
        checkAndShift(p3, list);
        Point2D p4 = new Point2D.Double(line.getX2()-p_x, p3.getY());
        checkAndShift(p4, list);
        Point2D p5 = new Point2D.Double(p4.getX(), line.getY2());
        checkAndShift(p5, list);
        Line2D line1 =  new Line2D.Double(line.getX1(), line.getY1(), p2.getX(), p2.getY());
        Line2D line2 =  new Line2D.Double(p2, p3);
        Line2D line3 =  new Line2D.Double(p3, p4);
        Line2D line4 =  new Line2D.Double(p4, p5);
        Line2D line5 =  new Line2D.Double(p5.getX(), p5.getY(), line.getX2(), line.getY2());
        Line2D[]array = {line1, line2, line3, line4, line5};
        linesList.addAll(Arrays.asList(array));
    }

    public void drawAllLines(){
        for(Line2D line: linesList ){
            gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }
    }
    public static void restoreShift(){
        X_SHIFT = 15;
    }

    private void checkAndShift(Point2D p, List<Line2D> list){
        if(ConnectionsDrawer.linesContainPoint(p, list)!=null){
            applyShift(p, list);
        }
    }
    private void applyShift(Point2D p, List<Line2D> list){
        Line2D line2D = ConnectionsDrawer.linesContainPoint(p, list);
        if (line2D == null){
            return;
        }
        if (line2D.getP1().getY() == p.getY()) {
            p.setLocation(p.getX(), p.getY() - 5);
        }
        if (line2D.getP2().getY() == p.getY()) {
            p.setLocation(p.getX(), p.getY() - 5);
        }
        if (line2D.getP1().getX() == p.getX()) {
            X_SHIFT += 5;
        }
        if (line2D.getP2().getX() == p.getX()) {
            X_SHIFT += 5;
        }

        applyShift(p, list);
    }
}
