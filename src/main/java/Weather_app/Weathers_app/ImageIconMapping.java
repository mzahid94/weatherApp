package Weather_app.Weathers_app;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageIconMapping {
    private static final String rootFolder = "file:icons\\";
    Image image;
    ImageView imageView;

    public Image getImage() {
        return image;
    }

    public ImageIconMapping(String condition) {
        this.image = new Image(rootFolder + condition + ".png");
        this.imageView = new ImageView(this.image);
    }

    public ImageView setHeightAndWidth(double height, double width) {
        this.imageView.setFitWidth(width);
        this.imageView.setFitHeight(height);
        return imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public static ImageView getIcon(String fileName) {
        ImageIconMapping imageIconMapping = new ImageIconMapping(rootFolder + fileName);
        return imageIconMapping.imageView;
    }

    public static ImageView getIconRes(String fileName, double height, double width) {
        ImageIconMapping imageIconMapping = new ImageIconMapping(rootFolder + fileName);
        return imageIconMapping.setHeightAndWidth(height, width);
    }
}
