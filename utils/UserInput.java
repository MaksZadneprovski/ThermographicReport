package utils;

import constants.Constants;

import java.util.Scanner;

public class UserInput {
    public static String imgPositionManager1, imgPositionManager2, imgPositionManager3, imgPositionManager4, imgPositionManager5;
    public static String imgSizeManager1, imgSizeManager2, imgSizeManager3, imgSizeManager4, imgSizeManager5, imgSizeManager6;
    public static String excelListCreator1, excelListCreator2, excelListCreator3;
    public void enterUserData(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Нужно ли изменить позиционирование картинок?        1 - Да        Enter - Нет");
        imgPositionManager1 = scanner.nextLine();
        System.out.println("Какое изображение вы хотите подвинуть? Фото - 1    Термограмма - 2");
        imgPositionManager2 = scanner.nextLine();
        System.out.println("Какой тип изображения вы хотите подвинуть? Вертикальное - 1    Горизонтальное - 2");
        imgPositionManager3 = scanner.nextLine();
        System.out.println("Как вы хотите подвинуть? Вертикально - 1    Горизонтально - 2");
        imgPositionManager4 = scanner.nextLine();
        System.out.println("Укажите на сколько нужно сместить");
        imgPositionManager5 = scanner.nextLine();

        System.out.println("Нужно ли изменить размер картинок?      1 - Да        Enter - Нет");
        imgSizeManager1 = scanner.nextLine();
        System.out.println("Тип? Фото - 1    Термограмма - 2");
        imgSizeManager2 = scanner.nextLine();
        System.out.println("Вертикальные - 1   Горизонтальные - 2");
        imgSizeManager3 = scanner.nextLine();
        System.out.println("Сейчас коэффициент " + Constants.kPhotoVertical + "\nУстановите новый");
        imgSizeManager4 = scanner.nextLine();
        System.out.println("Сейчас коэффициент " + Constants.kPhotoHorizontal + "\nУстановите новый");
        imgSizeManager5 = scanner.nextLine();
        System.out.println("Сейчас коэффициент " + Constants.kTermo + "\nУстановите новый");
        imgSizeManager6 = scanner.nextLine();

        System.out.println("Введите количество фото :");
        excelListCreator1 = scanner.nextLine();
        System.out.println("Введите через пробел номер фото с несколькими термограммами, если таких нет нажмите Enter");
        excelListCreator2 = scanner.nextLine();
        System.out.println("Если есть пункты с тремя и более термограммами, перечислите через пробел, если нет нажмите Enter");
        excelListCreator3 = scanner.nextLine();
        
        scanner.close();
    }
}
