package utils;

import constants.Constants;
import data.TempData;
import model.excel_entity.ImgEntity;
import model.excel_entity.MeasurementObject;
import model.excel_entity.MeasurementObjectBlock;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageToExcelMover {

    public static void addImageInList() throws IOException {
        for (MeasurementObject mo :TempData.measurementObjectList) {
            for (MeasurementObjectBlock mob : mo.getMeasurementObjectBlockList()) {
                if (mob.getPhoto() != null)  addImage(mob.getPhoto());
                if (mob.getTermo() != null)  addImage(mob.getTermo());
            }
        }
    }

    public  static void addImage(ImgEntity img) throws IOException {

        int col = img.getCol();
        int row = img.getRow();
        String pathImg = img.getPathImg();

        try (InputStream input = new FileInputStream(Constants.PATH_CREATED_PROTOKOL.toString())) {

            Workbook wb = WorkbookFactory.create(input);
            Sheet sheet = wb.getSheetAt(0);

            //FileInputStream obtains input bytes from the image file
            InputStream inputStream = new FileInputStream(pathImg);


            //Get the contents of an InputStream as a byte[].
            byte[] bytes = IOUtils.toByteArray(inputStream);
            //Adds a picture to the workbook
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            //close the input stream
            inputStream.close();

            //Returns an object that handles instantiating concrete classes
            CreationHelper helper = wb.getCreationHelper();

            //Creates the top-level drawing patriarch.
            Drawing drawing = sheet.createDrawingPatriarch();

            //Create an anchor that is attached to the worksheet
            ClientAnchor anchor = helper.createClientAnchor();
            //set top-left corner for the image
            anchor.setCol1(col);
            anchor.setRow1(row);

            //Creates a picture
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            //Reset the image to the original size
            pict.resize();

            //Write the Excel file
            FileOutputStream fileOut = null;
            fileOut = new FileOutputStream(Constants.PATH_CREATED_PROTOKOL.toString());
            wb.write(fileOut);
            fileOut.close();


        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}

