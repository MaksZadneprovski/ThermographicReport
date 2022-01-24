import constants.Constants;
import data.TempData;
import utils.*;
import java.io.IOException;
import java.text.ParseException;


public class Main2 {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

        TablesCreator t = new TablesCreator();
        t.getTablesFromContents();
        ExcelListCreator.creatingExcelList();
        t.createTables();
    }
}
