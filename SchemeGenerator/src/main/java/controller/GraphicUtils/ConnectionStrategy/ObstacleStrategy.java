package controller.GraphicUtils.ConnectionStrategy;

import controller.GraphicUtils.ConnectionsDrawer;
import javafx.scene.canvas.GraphicsContext;
import sun.net.www.content.text.plain;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 8/18/14.
 */
public class ObstacleStrategy extends  NoObstacleStrategy implements PathDrawingStrategy{

    @Override
    public List<Line2D> getLinesListWithShifts(Point2D start, Point2D end, GraphicsContext gc, List<Line2D> list) {
        if(ConnectionsDrawer.linesContainPoint(start, list)!=null){
            applyShift(start, list);
        }
        if(ConnectionsDrawer.linesContainPoint(end, list)!=null){
            applyShift(end, list);

        }


        return getLinesList(start, end, gc);
    }




    private void applyShift(Point2D p, List<Line2D> list){
        Line2D line2D = ConnectionsDrawer.linesContainPoint(p, list);
        if (line2D == null){
            return;
        }
        if (line2D.getP1().getY() == p.getY()) {
            p.setLocation(p.getX(), p.getY() + 5);
        }
        if (line2D.getP2().getY() == p.getY()) {
            p.setLocation(p.getX(), p.getY() + 5);
        }
        if (line2D.getP1().getX() == p.getX()) {
            super.X_SHIFT += 5;
        }
        if (line2D.getP2().getX() == p.getX()) {
            super.X_SHIFT += 5;
        }

        applyShift(p, list);
    }
}
