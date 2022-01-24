package utils;

import constants.Constants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ImgSizeManager {

    public static void setSizeImage(){
        if ( UserInput.imgSizeManager1.equals("1")) {
            int photoType = Integer.parseInt(UserInput.imgSizeManager2);
            if (photoType == 1) {
                if (Integer.parseInt(UserInput.imgSizeManager3) == 1) {
                    Constants.kPhotoVertical = Double.parseDouble(UserInput.imgSizeManager4);
                } else {
                    Constants.kPhotoHorizontal = Double.parseDouble(UserInput.imgSizeManager5);
                }
            }
            if(photoType == 2) {
                Constants.kTermo = Double.parseDouble(UserInput.imgSizeManager6);
            }
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(".").toAbsolutePath().getParent().resolve("sizes.txt"))) {
                bufferedWriter.write(Constants.kPhotoVertical + " "+ Constants.kPhotoHorizontal+" "+ Constants.kTermo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
