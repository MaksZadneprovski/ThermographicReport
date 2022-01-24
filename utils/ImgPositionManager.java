package utils;

import constants.Constants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImgPositionManager {

    public static void changeShift(){
        if (UserInput.imgPositionManager1.equals("1")){
            int photoType = UserInput.imgPositionManager2.equals("1") ? 1 :  2;
            int positionType = UserInput.imgPositionManager3.equals("1") ? 1 :  2;
            int orientationType = UserInput.imgPositionManager4.equals("1") ? 1 :  2;

            int shift = Integer.parseInt(UserInput.imgPositionManager5);

            if (photoType == 1 && positionType == 1 && orientationType == 1)  Constants.shiftThePhotoVertikally += shift;
            if (photoType == 1 && positionType == 1 && orientationType == 2)  Constants.shiftThePhotoHorizontally += shift;
            if (photoType == 1 && positionType == 2 && orientationType == 1)  Constants.shiftHorizontalPhotoVertically += shift;
            if (photoType == 1 && positionType == 2 && orientationType == 2)  Constants.shiftHorizontalPhotoHorizontally += shift;

            if (photoType == 2 && positionType == 1 && orientationType == 1)  Constants.shiftTheTermoVertikally += shift;
            if (photoType == 2 && positionType == 1 && orientationType == 2)  Constants.shiftTheTermoHorizontally += shift;
            if (photoType == 2 && positionType == 2 && orientationType == 1)  Constants.shiftHorizontalTermoVertically += shift;
            if (photoType == 2 && positionType == 2 && orientationType == 2)  Constants.shiftHorizontalTermoHorizontally += shift;


            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(".").toAbsolutePath().getParent().resolve("shifts.txt"))) {
                bufferedWriter.write(Constants.shiftHorizontalPhotoHorizontally + " "+ Constants.shiftHorizontalPhotoVertically+" "+ Constants.shiftThePhotoVertikally+" "+
                        Constants.shiftThePhotoHorizontally+" "+ Constants.shiftHorizontalTermoHorizontally +" "+ Constants.shiftHorizontalTermoVertically+" "+
                        Constants.shiftTheTermoVertikally  +" "+ Constants.shiftTheTermoHorizontally);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
