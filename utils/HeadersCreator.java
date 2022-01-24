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

import java.io.*;
import java.util.*;

public class HeadersCreator {


    public  void createHeaders() throws IOException {

        try (InputStream input = new FileInputStream(Constants.PATH_CREATED_PROTOKOL.toString())) {
            //Create blank workbook
            Workbook wb = WorkbookFactory.create(input);

            //Create a blank sheet
            Sheet sheet = wb.getSheetAt(0);

            //Create row object
            Row row;

            int count = 1;

            for (MeasurementObject mo :TempData.measurementObjectList) {
                row = sheet.getRow(mo.getMeasurementObjectBlockList().get(0).getPosition() - 2);
                Cell cell = row.getCell(5);
                cell.setCellValue( count++ +". "+mo.getHeader());
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

    public void completeHeadersQueue() throws IOException {
        TempData.headersQueue = getHeadersFromContents();
    }


    public Queue<String> getHeadersFromContents() throws IOException {
        Queue<String> headersQueue = new LinkedList<>();
        XSSFRow row;
        FileInputStream fis = new FileInputStream(Constants.PATH_HEADERS.toString());

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = spreadsheet.iterator();

        while (rowIterator.hasNext()) {
            row = (XSSFRow) rowIterator.next();
            String s = row.getCell(0).getStringCellValue();
            headersQueue.add(s);
        }
        fis.close();
        return headersQueue;
    }

}
