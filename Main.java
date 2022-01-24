
import data.TempData;
import security.HttpRequest;
import security.KeyVerification;
import utils.*;
import constants.Constants;
import file_visitors.AddPhotoIntoQueueFileVisitor;
import file_visitors.ResizeAndMoveImgFileVisitor;
import file_visitors.AddTermoIntoQueueFileVisitor;


import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

        //new HttpRequest().doRequest();
        //new KeyVerification().verify();

        // Установка смещения фото
        ImgPositionManager.changeShift();

        // Установка коэф. размера
        ImgSizeManager.setSizeImage();

        // Конвертация PDF to XLSX
        new PdfToXlsxConverter().convert2(Constants.PATH_TABLESPDF.toString(),Constants.PATH_TABLESXLSX.toString());

        // Парсинг содержания и добавление заголовков в очередь
        System.out.println("    1 из 14 Парсинг содержания и добавление заголовков в очередь");
        new HeadersCreator().completeHeadersQueue();

        // Парсинг таблиц и добавление температур в очередь
        System.out.println("    2 из 14 Парсинг таблиц и добавление температур в очередь");
        new TablesCreator().getTablesFromContents();

        // Проверка наличия папок для создания отчета
        System.out.println("    3 из 14 Проверка наличия папок для создания отчета");
        FileHelper.checkPaths();

        // Удаление старых изображений
        System.out.println("    4 из 14 Удаление старых изображений");
        FileHelper.deleteSaveFiles();

        //Удаление старого отчета
        System.out.println("    5 из 14 Удаление старого отчета");
        FileHelper.deleteTempForProject();

        // Подготовка шаблона
        System.out.println("    6 из 14 Подготовка шаблона");
        FileHelper.movingTemplates();

        // Конвертация изображений
        System.out.println("    7 из 14 Конвертация изображений");
        Files.walkFileTree(Constants.PATH_PICTURES, new ResizeAndMoveImgFileVisitor());

        // Проверка наличия сковертированных фото для отчета
        System.out.println("    8 из 14 Проверка наличия сковертированных фото для отчета");
        FileHelper.changedPhotoAvailabilityCheck();

        // Распределение и добавление фото и термограмм в разные списки
        System.out.println("    9 из 14 Распределение и добавление фото и термограмм в разные списки");
        Files.walkFileTree(Constants.PATH_TO_SAVED_PHOTO, new AddPhotoIntoQueueFileVisitor());
        Files.walkFileTree(Constants.PATH_TO_SAVED_TERMO, new AddTermoIntoQueueFileVisitor());

        System.out.println("    10 из 14 Подготовка структуры файла отчета ");
        // Подготовка файла шаблона
        ExcelListCreator.creatingExcelList();

        // Вставка заголовков в отчет
        System.out.println("    11 из 14 Вставка заголовков в отчет");
        new HeadersCreator().createHeaders();

        // Вставка таблиц в отчет
        System.out.println("    12 из 14 Вставка таблиц в отчет");
        new TablesCreator().createTables();

        // Вставка термограмм и фото в отчет
        System.out.println("    13 из 14 Вставка термограмм и фото в отчет");
        ImageToExcelMover.addImageInList();

        // Удаление ненужных файлов
        System.out.println("    14 из 14 Удаление ненужных файлов");
        FileHelper.deleteXLSXReport();
        FileHelper.deleteSaveFiles();

        System.out.println("    Отчет успешно создан ");
        Thread.sleep(4000);
    }
}
