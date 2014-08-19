package controller.utils;

import controller.GraphicUtils.OperatorElementWrapper;
import javafx.embed.swing.SwingFXUtils;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import model.AppContext;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/11/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    public static ResourceBundle getMessages() {
        return getLocalizedResources("messages.MessagesBundle") ;
    }

    private static ResourceBundle getLocalizedResources(String name){
        return ResourceBundle.getBundle(name, AppContext.getInstance().getCurrentLocale());
    }
    public static List<Integer> createIntList(int first, int last){
        List<Integer> list = new ArrayList<Integer>();

        for (int i = first; i <= last; i++ ){
            list.add(i);
        }
        return list;
    }
    public static void print(final Node node) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        node.getTransforms().add(new Scale(scaleX, scaleY));

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }
    }
    public static void saveToFile(Image image){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        //System.out.println(pic.getId());
        File file = fileChooser.showSaveDialog(null);
        /*Image image = new Image("http://docs.oracle.com/javafx/"
                + "javafx/images/javafx-documentation.png");*/
        ImageView pic = new ImageView();
        pic.setImage(image);

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(pic.getImage(),
                        null), "png", file);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static Point2D getOutPoint(OperatorElementWrapper out){
        double x = out.getX() + out.getWidth();
        double y = out.getY() + out.getHeight()/2;
        return new Point2D.Double(x, y);
    }

    public static Point2D getInPoint(OperatorElementWrapper in){
        double x = in.getX();
        double y = in.getY() + in.getHeight()/2;
        return new Point2D.Double(x, y);
    }

    public static Point2D getTopPoint(OperatorElementWrapper e){
        double x = e.getX()+e.getWidth()/2;
        double y = e.getY();
        return new Point2D.Double(x, y);
    }

    public static Point2D getBottomPoint(OperatorElementWrapper e){
        double x = e.getX() + e.getWidth()/2;
        double y = e.getY() + e.getHeight();
        return new Point2D.Double(x, y);
    }
}
