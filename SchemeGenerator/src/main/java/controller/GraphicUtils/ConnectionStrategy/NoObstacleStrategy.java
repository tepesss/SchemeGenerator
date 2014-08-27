package controller.GraphicUtils.ConnectionStrategy;

import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 8/18/14.
 */
public class NoObstacleStrategy implements PathDrawingStrategy{
    public static int X_SHIFT = 15;
    protected List<Line2D> linesList = new ArrayList<>();
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
        checkForVertical(end, list);
        return getLinesList( start,  end,  gc);
    }

    private void divideLine(Line2D line){
        Line2D line1 =  new Line2D.Double(line.getX1(), line.getY1(), line.getX2()- X_SHIFT, line.getY1());
        Line2D line2 =  new Line2D.Double(line.getX2()- X_SHIFT, line.getY1(), line.getX2()- X_SHIFT, line.getY2());
        Line2D line3 =  new Line2D.Double(line.getX2()- X_SHIFT, line.getY2(), line.getX2(), line.getY2());
        Line2D[]array = {line1, line2, line3};
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
    private void checkForVertical(Point2D end, List<Line2D> list) {
        for (Line2D line2D : list){
            if(end.getX() - X_SHIFT == line2D.getX1() || end.getX() - X_SHIFT == line2D.getX2()){
                X_SHIFT -= 3;
                checkForVertical(end, list);
            }
        }
    }
}
