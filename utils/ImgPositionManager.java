package utils;

import constants.Constants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ImgPositionManager {

    public static void changeShift(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Нужно ли изменить позиционирование картинок?        1 - Да        Enter - Нет");
        if (scanner.nextLine().equals("1")){
            System.out.println("Какое изображение вы хотите подвинуть? Фото - 1    Термограмма - 2");
            int photoType = scanner.next().equals("1") ? 1 :  2;
            System.out.println("Какой тип изображения вы хотите подвинуть? Вертикальное - 1    Горизонтальное - 2");
            int positionType = scanner.next().equals("1") ? 1 :  2;
            System.out.println("Как вы хотите подвинуть? Вертикально - 1    Горизонтально - 2");
            int orientationType = scanner.next().equals("1") ? 1 :  2;
            System.out.println("Укажите на сколько нужно сместить");

            int shift = Integer.parseInt(scanner.next());

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
