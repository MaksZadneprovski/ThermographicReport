package utils;

import data.TempData;
import model.excel_entity.MeasurementObject;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ExcelListCreator {

    public static void creatingExcelList(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество фото :");
        int quantityObj = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите через пробел номер фото с несколькими термограммами, если таких нет нажмите Enter");
        String str = scanner.nextLine();

        int[] arr = new int[200];
        int[] arr2 = new int[100];

        if (!str.isEmpty()) {
            arr = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println("Если есть пункты с тремя и более термограммами, перечислите через пробел, если нет нажмите Enter");
            String str2 = scanner.nextLine();
            if (!str2.isEmpty()){
                arr2 = Arrays.stream(str2.split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        }

        for (int i = 1; i <= quantityObj ; i++) {
            int n = i;
            if (IntStream.of(arr).anyMatch(x -> x == n)){

                if(IntStream.of(arr2).anyMatch(x -> x == n)){

                    System.out.println("Сколько термограмм  в "+ i +" пункте");
                    TempData.measurementObjectList.add(new MeasurementObject( Integer.parseInt(scanner.next())));

                }else {
                    TempData.measurementObjectList.add(new MeasurementObject( 2));
                }
            }
            else {
                TempData.measurementObjectList.add(new MeasurementObject(1));
            }
        }

        //scanner.close();
    }
}
