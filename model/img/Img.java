package model.img;

import lombok.Data;
import utils.ImageHelper;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Data
public class Img {
    private Path path;
    private String fileName;
    private int width;
    private int height;
    private Path pathToWrite;
    private Type type;
    private BufferedImage bufferedImageInput;

    public Img(Path path) throws IOException {
        this.path = path;
        this.fileName = path.getFileName().toString();
        bufferedImageInput = ImageIO.read(new File(path.toString()));
        ImageHelper.determineSizeAndType(this);
    }



}
