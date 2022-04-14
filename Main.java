
import data.TempData;
import utils.*;
import constants.Constants;
import file_visitors.AddPhotoIntoQueueFileVisitor;
import file_visitors.ResizeAndMoveImgFileVisitor;
import file_visitors.AddTermoIntoQueueFileVisitor;


import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException, AWTException {

        // Проверка наличия папок для создания отчета
        System.out.println("Проверка наличия папок для создания отчета");
        FileHelper.checkPaths();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Нужно ли конвертировать картинки?      1 - Да        Enter - Нет");
        if ( scanner.nextLine().equals("1")) {

            // Удаление старых изображений
            System.out.println("Удаление старых изображений");
            FileHelper.deleteSaveFiles();

            // Конвертация изображений
            System.out.println("Конвертация изображений");
            try {
                Files.walkFileTree(Constants.PATH_PICTURES, new ResizeAndMoveImgFileVisitor());
                showMessage("Конвертация картинок завершена");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // Парсинг содержания и добавление заголовков в очередь
        System.out.println("Парсинг содержания и добавление заголовков в очередь");
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

        // Проверка наличия сковертированных фото для отчета
        System.out.println("Проверка наличия сковертированных фото для отчета");
        FileHelper.changedPhotoAvailabilityCheck();

        // Распределение и добавление фото и термограмм в разные списки
        System.out.println("Распределение и добавление фото и термограмм в разные списки");
        Files.walkFileTree(Constants.PATH_TO_SAVED_PHOTO, new AddPhotoIntoQueueFileVisitor());
        Files.walkFileTree(Constants.PATH_TO_SAVED_TERMO, new AddTermoIntoQueueFileVisitor());

        //  Для отладки
        //System.out.println(TempData.pathListTermo);


        // Подготовка структуры файла отчета
        System.out.println("Подготовка структуры файла отчета ");
        ExcelListCreator.creatingExcelList();

        // Вывод в консоль для отладки
        //TempData.measurementObjectList.forEach(x -> x.getMeasurementObjectBlockList().forEach(System.out::println));

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
        //System.out.println("Удаление ненужных файлов");
        //FileHelper.deleteXLSXReport();
        //FileHelper.deleteSaveFiles();

        showMessage("Отчет создан");
        System.exit(0);

    }
    static void showMessage(String message) throws AWTException {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            java.awt.Image image = Toolkit.getDefaultToolkit().getImage("images/tray.gif");
            TrayIcon trayIcon = new TrayIcon(image);
            tray.add(trayIcon);
            trayIcon.displayMessage("Report", message,
                    TrayIcon.MessageType.INFO);
        }
    }
}
