package utils;

import constants.Constants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ImgSizeManager {

    public static void setSizeImage(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Нужно ли изменить размер картинок?      1 - Да        Enter - Нет");
        if ( scanner.nextLine().equals("1")) {
            System.out.println("Тип? Фото - 1    Термограмма - 2");
            int photoType = Integer.parseInt(scanner.next());
            if (photoType == 1) {
                System.out.println("Вертикальные - 1   Горизонтальные - 2");
                if (Integer.parseInt(scanner.next()) == 1) {
                    System.out.println("Сейчас коэффициент " + Constants.kPhotoVertical + "\nУстановите новый");
                    Constants.kPhotoVertical = Double.parseDouble(scanner.next());
                } else {
                    System.out.println("Сейчас коэффициент " + Constants.kPhotoHorizontal + "\nУстановите новый");
                    Constants.kPhotoHorizontal = Double.parseDouble(scanner.next());
                }
            }
            if(photoType == 2) {
                System.out.println("Сейчас коэффициент " + Constants.kTermo + "\nУстановите новый");
                Constants.kTermo = Double.parseDouble(scanner.next());
            }
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(".").toAbsolutePath().getParent().resolve("sizes.txt"))) {
                bufferedWriter.write(Constants.kPhotoVertical + " "+ Constants.kPhotoHorizontal+" "+ Constants.kTermo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
