package utils;

import constants.Constants;
import data.TempData;
import model.excel_entity.MeasurementObject;
import model.excel_entity.MeasurementObjectBlock;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class TablesCreator {

    public  void createTables() throws IOException, ParseException {

        try (InputStream input = new FileInputStream(Constants.PATH_CREATED_PROTOKOL.toString())) {
            //Create blank workbook
            Workbook wb = WorkbookFactory.create(input);

            //Create a blank sheet
            Sheet sheet = wb.getSheetAt(0);

            //Create row object
            Row row1;
            Row row2;
            Row row3;

            NumberFormat nf = NumberFormat.getInstance();

            for (MeasurementObject mo :TempData.measurementObjectList) {
                for (MeasurementObjectBlock mob : mo.getMeasurementObjectBlockList()) {
                    if (mob.isUpBlock()){
                        row1 = sheet.getRow(mob.getPosition() + 17);
                        row2 = sheet.getRow(mob.getPosition() + 18);
                        row3 = sheet.getRow(mob.getPosition() + 19);
                    }else {
                        row1 = sheet.getRow(mob.getPosition() + 18);
                        row2 = sheet.getRow(mob.getPosition() + 19);
                        row3 = sheet.getRow(mob.getPosition() + 20);
                    }
                    Cell cell1 = row1.getCell(1);
                    Cell cell2 = row2.getCell(1);
                    Cell cell3 = row3.getCell(1);
                    if (mob.getMidTemperature() != null && mob.getLowTemperature() != null && mob.getMaxTemperature() != null ){
                        cell1.setCellValue(nf.parse(mob.getMidTemperature()).doubleValue());
                        cell2.setCellValue(nf.parse(mob.getLowTemperature()).doubleValue());
                        cell3.setCellValue(nf.parse(mob.getMaxTemperature()).doubleValue());
                    }
                }
            }

            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(Constants.PATH_CREATED_PROTOKOL.toString());
            //workbook.write(out);
            wb.write(out);
            out.close();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public Queue<String> getTablesFromContents() throws IOException {
        Queue<String> tablesQueue = new LinkedList<>();
        XSSFRow row;
        FileInputStream fis = new FileInputStream(Constants.PATH_TABLES.toString());

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = spreadsheet.iterator();

        while (rowIterator.hasNext()) {
            row = (XSSFRow) rowIterator.next();
            String s = row.getCell(0).getStringCellValue();
            if (s.startsWith("Точка")){
                TempData.thermogramCenterSpots.add(getTemperatureFromText(s,3));
            }else if (s.startsWith("Самая холодная")){

                TempData.thermogramColdSpots.add(getTemperatureFromText(s,4));
            }else if (s.startsWith("Самая теплая")){

                TempData.thermogramHotSpots.add(getTemperatureFromText(s,4));
            }
        }
        fis.close();
        return tablesQueue;
    }

    public String getTemperatureFromText(String s, int i){
        s = s.trim().replaceAll("\\s{2,}", " ");
        String [] strings = s.split(" ");
        return strings[i];
    }
}
