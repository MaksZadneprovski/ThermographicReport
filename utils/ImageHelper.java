package utils;

import constants.Constants;
import model.img.Img;
import model.img.Type;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHelper {

    public static  void resizeAndMoveImg(Img img) throws IOException {

        BufferedImage bufferedImageOutput = new BufferedImage(img.getWidth(), img.getHeight(), img.getBufferedImageInput().getType());

        Graphics2D g2d = bufferedImageOutput.createGraphics();

        if (img.getType() == Type.PHOTO_VERTICAL){
            img.setBufferedImageInput(Scalr.rotate(img.getBufferedImageInput(), Scalr.Rotation.CW_90));
        }
        g2d.drawImage(img.getBufferedImageInput(), 0, 0, img.getWidth(), img.getHeight(), null);
        g2d.dispose();

        ImageIO.write(bufferedImageOutput, Constants.formatName, new File(Util.generateName(img)));
    }

    public static void determineSizeAndType(Img img){
        if ( img.getPath().getParent().toString().equals(Constants.PATH_PHOTO.toString())){

            img.setType(Type.PHOTO_VERTICAL);
            img.setPathToWrite(Constants.PATH_TO_SAVED_PHOTO);
            img.setHeight(Constants.heightPhotoVertical);
            img.setWidth(Constants.widthPhotoVertical);

        }else if (img.getPath().getParent().toString().equals(Constants.PATH_PHOTO_HORIZONTAL.toString())){

            img.setType(Type.PHOTO_HORIZONTAL);
            img.setPathToWrite(Constants.PATH_TO_SAVED_PHOTO);
            img.setHeight(Constants.heightPhotoHorizontal);
            img.setWidth(Constants.widthPhotoHorizontal);

        }else if (img.getPath().getParent().toString().equals(Constants.PATH_TERMO.toString())) {

            img.setPathToWrite(Constants.PATH_TO_SAVED_TERMO);
            img.setHeight(Util.determineHeight(img));
            img.setWidth(Util.determineWidth(img));
        }
    }
}
