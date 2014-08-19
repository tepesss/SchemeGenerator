package controller.GraphicUtils;

import controller.GraphicUtils.ConnectionStrategy.NoObstacleStrategy;
import controller.GraphicUtils.ConnectionStrategy.ObstacleStrategy;
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
        }else if(out.getX() == in.getX()){
            // []
            // |
            // []
            if (out.getY()>in.getY()){
                startPoint = Utils.getBottomPoint(out);
                endPoint  = Utils.getTopPoint(in);
            }else{
                startPoint = Utils.getTopPoint(out);
                endPoint  = Utils.getBottomPoint(in);
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
        }else if(out.getX() > in.getX() && out.getY() != in.getY()){
            startPoint = Utils.getOutPoint(out);
            endPoint = Utils.getInPoint(in);
        }else{
            startPoint = Utils.getOutPoint(out);
            endPoint = Utils.getInPoint(in);
        }

        drawConnection(startPoint, endPoint);
    }

    public void drawConnection(Point2D start, Point2D end){
        if(start.getY() == end.getY()){
            linesList.addAll(new StraightStrategy().getLinesList(start, end, gc));
        }else if(!isLineOverlapping(start, end)){
            linesList.addAll(new NoObstacleStrategy().getLinesList(start, end, gc));
        }else if(isLineOverlapping(start, end)){
            linesList.addAll(new ObstacleStrategy().getLinesList(start, end, gc));
        }

    }
    public boolean isLineOverlapping(Point2D start, Point2D end){
        boolean overlaps = false;
        if(linesContainPoint(start)!=null){
            start.setLocation(start.getX(), start.getY()+ VERTICAL_SHIFT);
            isLineOverlapping(start, end);
        }
        if(linesContainPoint(end)!=null){
            end.setLocation(end.getX(), end.getY()+ VERTICAL_SHIFT);
            isLineOverlapping(start, end);
        }
        return overlaps;
    }

    private Line2D linesContainPoint(Point2D p){
        for(Line2D line2D: linesList){
            if(line2D.getP1().equals(p)||line2D.getP2().equals(p))
                return line2D;
        }
        return null;
    }


}
