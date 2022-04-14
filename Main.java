
import data.TempData;
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
        long t0 = System.currentTimeMillis();

        // Проверка наличия папок для создания отчета
        System.out.println("    3 из 14 Проверка наличия папок для создания отчета");
        FileHelper.checkPaths();

        // Удаление старых изображений
        System.out.println("    4 из 14 Удаление старых изображений");
        FileHelper.deleteSaveFiles();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Конвертация изображений
                System.out.println("    7 из 14 Конвертация изображений");
                try {
                    Files.walkFileTree(Constants.PATH_PICTURES, new ResizeAndMoveImgFileVisitor());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        // Парсинг содержания и добавление заголовков в очередь
        System.out.println("    1 из 14 Парсинг содержания и добавление заголовков в очередь");
        new HeadersCreator().completeHeadersQueue();

        // Парсинг таблиц и добавление температур в очередь
        System.out.println("Парсинг таблиц и добавление температур в очередь");
        new TablesCreator().getTablesFromContents();


        // Установка смещения фото
        ImgPositionManager.changeShift();
        // Установка коэф. размера
        ImgSizeManager.setSizeImage();


        // Конвертация PDF to XLSX
        PdfToXlsxConverter.convert2(Constants.PATH_TABLESPDF.toString(),Constants.PATH_TABLESXLSX.toString());

        //Удаление старого отчета
        System.out.println("Удаление старого отчета");
        FileHelper.deleteTempForProject();

        // Подготовка шаблона
        System.out.println("Подготовка шаблона");
        FileHelper.movingTemplates();

        thread.join();
        // Проверка наличия сковертированных фото для отчета
        System.out.println("Проверка наличия сковертированных фото для отчета");
        FileHelper.changedPhotoAvailabilityCheck();

        // Распределение и добавление фото и термограмм в разные списки
        System.out.println("Распределение и добавление фото и термограмм в разные списки");
        Files.walkFileTree(Constants.PATH_TO_SAVED_PHOTO, new AddPhotoIntoQueueFileVisitor());
        Files.walkFileTree(Constants.PATH_TO_SAVED_TERMO, new AddTermoIntoQueueFileVisitor());

        System.out.println(TempData.pathListTermo);


        // Подготовка структуры файла отчета
        System.out.println("Подготовка структуры файла отчета ");
        ExcelListCreator.creatingExcelList();
        TempData.measurementObjectList.forEach(x -> x.getMeasurementObjectBlockList().forEach(System.out::println));

        // Вставка заголовков в отчет
        System.out.println("Вставка заголовков в отчет");
        new HeadersCreator().createHeaders();

        // Вставка таблиц в отчет
        System.out.println("Вставка таблиц в отчет");
        new TablesCreator().createTables();

        // Вставка термограмм и фото в отчет
        System.out.println("Вставка термограмм и фото в отчет");
        ImageToExcelMover.addImageInList();

        // Удаление ненужных файлов
        System.out.println("Удаление ненужных файлов");
        //FileHelper.deleteXLSXReport();
        //FileHelper.deleteSaveFiles();

        System.out.println("Отчет успешно создан ");

        long t4 = System.currentTimeMillis();

        System.out.println(t4-t0);


    }
}
