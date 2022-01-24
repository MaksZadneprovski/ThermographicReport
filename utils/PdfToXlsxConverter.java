package utils;

import com.aspose.pdf.Document;
import com.aspose.pdf.ExcelSaveOptions;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class PdfToXlsxConverter {

    public void convert(String pathOf, String pathTo){
        // Load source PDF file
        Document doc = new Document(pathOf);
        // Set Excel options
        ExcelSaveOptions options = new ExcelSaveOptions();
        // Set minimizing option
        options.setMinimizeTheNumberOfWorksheets(true);
        // Set output format
        options.setFormat(ExcelSaveOptions.ExcelFormat.XLSX);
        // Convert PDF to XLSX
        doc.save(pathTo, options);
    }

    public static void convert2(String pathOf, String pathTo) throws IOException
    {
        try
        {
            String sheetName = "Sheet1";//name of sheet

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(sheetName) ;
            XSSFCell cell= null;
            int r=0;

            PDDocument document = PDDocument.load(new File(pathOf));// here file1.pdf is the name of pdf file which we want to read....
            document.getClass();
            if (!document.isEncrypted())
            {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();
                String str = Tstripper.getText(document);

                Scanner scnLine = null;
                scnLine = new Scanner(str);

                String line="";

                while (scnLine.hasNextLine())
                {

                    line = scnLine.nextLine();
                    XSSFRow row = sheet.createRow(r);

                    Scanner scnWord = new Scanner(line);

                    StringBuilder stringBuilder = new StringBuilder();
                    while (scnWord.hasNext()){
                        str=scnWord.next();
                        stringBuilder.append(str).append(" ");
                    }
                    cell = row.createCell(0);
                    cell.setCellValue(stringBuilder.toString());

                    r++;
                }
            }

            FileOutputStream fileOut = new FileOutputStream(pathTo);

            //write this workbook to an Outputstream.
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();

            document.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
