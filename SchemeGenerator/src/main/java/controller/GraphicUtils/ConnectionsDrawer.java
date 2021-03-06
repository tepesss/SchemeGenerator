package controller.GraphicUtils;

import controller.GraphicUtils.ConnectionStrategy.NoObstacleStrategy;
import controller.GraphicUtils.ConnectionStrategy.ObstacleStrategy;
import controller.GraphicUtils.ConnectionStrategy.RetrospectionStrategy;
import controller.GraphicUtils.ConnectionStrategy.StraightStrategy;
import controller.utils.Utils;
import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 7/30/14.
 */
public class ConnectionsDrawer {
    public static final int VERTICAL_SHIFT = 5;
    private GraphicsContext gc;
    private List<Line2D> linesList = new ArrayList<>();

    public ConnectionsDrawer(GraphicsContext gc){
        this.gc = gc;
    }

    public void connect(OperatorElementWrapper out, OperatorElementWrapper in){
        Point2D startPoint;
        Point2D endPoint;
        //
        // []-[]
        //
        if(out.getY() == in.getY()){
            startPoint = Utils.getOutPoint(out);
            endPoint = Utils.getInPoint(in);
        }
        /*else if(out.getX() == in.getX()){
            // []
            // |
            // []
            if (out.getY()>in.getY()){
                startPoint = Utils.getTopPoint(out);
                endPoint  = Utils.getBottomPoint(in);

            }else{
                startPoint = Utils.getBottomPoint(out);
                endPoint  = Utils.getTopPoint(in);
            }

        // []-
        //    |
        //     -[]
        }else if(out.getX() < in.getX() && out.getY() != in.getY()){
            startPoint = Utils.getOutPoint(out);
            endPoint = Utils.getInPoint(in);
        //    |-[]
        //    ---------
        //            |
        //         []--
        }*/
        else if(out.getX() >= in.getX() && out.getY() != in.getY()){
            startPoint = Utils.getOutPoint(out);
            endPoint = Utils.getInPoint(in);
        }else{
            startPoint = Utils.getOutPoint(out);
            endPoint = Utils.getInPoint(in);
        }

        drawConnection(startPoint, endPoint);
    }

    public void drawConnection(Point2D start, Point2D end) {
        if (start.getY() == end.getY()) {
            if(isLineOverlapping(start, end)){
                linesList.addAll(new StraightStrategy().getLinesListWithShifts(start, end, gc, linesList));
            }else{
                linesList.addAll(new StraightStrategy().getLinesList(start, end, gc));
            }
        } else if (start.getX() >= end.getX()){
            linesList.addAll(new RetrospectionStrategy().getLinesListWithShifts(start, end, gc, linesList));
        } else if (!isLineOverlapping(start, end)) {
            linesList.addAll(new NoObstacleStrategy().getLinesListWithShifts(start, end, gc, linesList));
        }else if(isLineOverlapping(start, end)){
            linesList.addAll(new ObstacleStrategy().getLinesListWithShifts(start, end, gc, linesList));
        }

    }

    public boolean isLineOverlapping(Point2D start, Point2D end) {
        Point2D point = new Point2D.Double(end.getX() - NoObstacleStrategy.X_SHIFT, end.getY());
        if (linesContainPoint(start, linesList) != null)
            return true;
        if (linesContainPoint(end, linesList) != null)
            return true;


        return false;
    }

    public static Line2D linesContainPoint(Point2D p, List<Line2D> lines) {
        for (Line2D line2D : lines) {

            if (line2D.getP1().equals(p) || line2D.getP2().equals(p) )
                return line2D;
        }
        return null;
    }





}
