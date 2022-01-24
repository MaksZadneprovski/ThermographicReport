package utils;

import constants.Constants;
import data.TempData;
import model.excel_entity.MeasurementObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ExcelListCreator {

    public static void creatingExcelList(){
        Scanner scanner = new Scanner(System.in);
        int quantityObj = Integer.parseInt(UserInput.excelListCreator1);

        String str = UserInput.excelListCreator2;

        int[] arr = new int[200];
        int[] arr2 = new int[100];

        if (!str.isEmpty()) {
            arr = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
            String str2 = UserInput.excelListCreator3;
            arr2 = Arrays.stream(str2.split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 1; i <= quantityObj ; i++) {
            int n = i;
            if (IntStream.of(arr).anyMatch(x -> x == n)){

                if(IntStream.of(arr2).anyMatch(x -> x == n)){

                    System.out.println("Сколько термограмм  в "+ i +" пункте");
                    TempData.measurementObjectList.add(new MeasurementObject( Integer.parseInt(scanner.nextLine())));

                }else {
                    TempData.measurementObjectList.add(new MeasurementObject( 2));
                }
            }
            else {
                TempData.measurementObjectList.add(new MeasurementObject(1));
            }
        }
        scanner.close();
    }
}
