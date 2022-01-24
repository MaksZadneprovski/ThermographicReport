package file_visitors;

import model.img.Img;
import utils.ImageHelper;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class ResizeAndMoveImgFileVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (!Files.isDirectory(file)){
            Img img = new Img(file);
            ImageHelper.resizeAndMoveImg(img);
        }
        return FileVisitResult.CONTINUE;
    }
}