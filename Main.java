
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

        new KeyVerification().verify();

        // Установка смещения фото
        ImgPositionManager.changeShift();

        // Установка коэф. размера
        ImgSizeManager.setSizeImage();

        // Парсинг содержания и добавление заголовков в очередь
        System.out.println("Парсинг содержания и добавление заголовков в очередь");
        new HeadersCreator().completeHeadersQueue();

        // Парсинг таблиц и добавление температур в очередь
        System.out.println("Парсинг таблиц и добавление температур в очередь");
        new TablesCreator().getTablesFromContents();

        long timeStart = System.currentTimeMillis();

        // Проверка наличия папок для создания отчета
        System.out.println("Проверка наличия папок для создания отчета");
        FileHelper.checkPaths();

        // Удаление старых изображений
        System.out.println("Удаление старых изображений");
        FileHelper.deleteSaveFiles();

        //Удаление старого отчета
        System.out.println("Удаление старого отчета");
        FileHelper.deleteTempForProject();

        FileHelper.movingTemplates();


        // Конвертация изображений
        System.out.println("Конвертация изображений");
        Files.walkFileTree(Constants.PATH_PICTURES, new ResizeAndMoveImgFileVisitor());

        // Проверка наличия сковертированных фото для отчета
        System.out.println("Проверка наличия сковертированных фото для отчета");
        FileHelper.changedPhotoAvailabilityCheck();

        // Распределение и добавление фото и термограмм в разные списки
        System.out.println("Распределение и добавление фото и термограмм в разные списки");
        Files.walkFileTree(Constants.PATH_TO_SAVED_PHOTO, new AddPhotoIntoQueueFileVisitor());
        Files.walkFileTree(Constants.PATH_TO_SAVED_TERMO, new AddTermoIntoQueueFileVisitor());

        System.out.println("Подготовка файла шаблона");
        // Подготовка файла шаблона
        ExcelListCreator.creatingExcelList();

        // Вставка заголовков в отчет
        System.out.println("Вставка заголовков в отчет");
        new HeadersCreator().createHeaders();

        // Вставка таблиц в отчет
        System.out.println("Вставка таблиц в отчет");
        new TablesCreator().createTables();

        // Вставка термограмм и фото в отчет
        System.out.println("Вставка термограмм и фото в отчет");
        ImageToExcelMover.addImageInList();

        TempData.measurementObjectList.forEach(System.out::println);

        long timeFinish = System.currentTimeMillis();
        System.out.println("Отчет успешно создан за : "+(timeFinish-timeStart)/1000 +"с "+ (timeFinish-timeStart)%10 + "мс");
        Thread.sleep(4000);

    }
}
