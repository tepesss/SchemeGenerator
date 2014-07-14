package model;

import javafx.scene.image.Image;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/15/13
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class OutputModel extends BaseModel {
    private Image image;

    public OutputModel() {
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
