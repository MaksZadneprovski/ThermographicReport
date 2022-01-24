package utils;

import constants.Constants;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {

    public static void checkPaths() throws IOException {
        createPathIfNotExist(Constants.PATH_PICTURES);
        createPathIfNotExist(Constants.PATH_TERMO);
        createPathIfNotExist(Constants.PATH_PHOTO);
        createPathIfNotExist(Constants.PATH_PHOTO_HORIZONTAL);
        createPathIfNotExist(Constants.PATH_TO_SAVED_TERMO);
        createPathIfNotExist(Constants.PATH_TO_SAVED_PHOTO);
    }
    public static void createPathIfNotExist(Path path) throws IOException {

        if ( !Files.exists(path)){
            Files.createDirectory(path);
        }
    }

    public static void changedPhotoAvailabilityCheck(){
        if (!Files.exists(Constants.PATH_TO_SAVED_PHOTO) || !Files.exists(Constants.PATH_TO_SAVED_TERMO) ) try {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Отсутствуют фото или термограммы для отчета");
        }
    }

    // Удаление старых изображений
    public static void deleteSaveFiles()  {
        try {
            FileUtils.deleteDirectory(Constants.PATH_TO_SAVED_TERMO.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileUtils.deleteDirectory(Constants.PATH_TO_SAVED_PHOTO.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            createPathIfNotExist(Constants.PATH_TO_SAVED_TERMO);
            createPathIfNotExist(Constants.PATH_TO_SAVED_PHOTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Удаляет файл отчета из проекта
    public static void deleteTempForProject()  {
        try {
            Files.deleteIfExists(Constants.PATH_CREATED_PROTOKOL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deleteXLSXReport() {
        try {
            Files.deleteIfExists(Constants.PATH_TABLESXLSX);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Перемещает шаблон отчета из папки в папку проекта
    public static void movingTemplates() throws IOException {
        Files.copy(Constants.PATH_EXCEL_TEMP,Constants.PATH_CREATED_PROTOKOL);
    }
    // Перемещает готовый отчет из папки проекта в папку загрузок
    public static void movingProtocol() throws IOException {
        Files.copy(Constants.PATH_EXCEL_TEMP,Constants.PATH_CREATED_PROTOKOL);
    }
}
