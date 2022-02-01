import utils.PdfToXlsxConverter;

import java.io.IOException;
import java.text.ParseException;


public class Main2 {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

        PdfToXlsxConverter.convert2("D:\\\\NoosferaProgram\\\\Новый отчет.pdf", "D:\\\\NoosferaProgram\\\\Автоочет.xlsx");
    }
}
