package constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Constants {

        public static String formatName = "jpg";

        public static int shiftHorizontalPhotoHorizontally;
        public static int shiftHorizontalPhotoVertically;
        public static int shiftThePhotoVertikally;
        public static int shiftThePhotoHorizontally;

        public static int shiftHorizontalTermoHorizontally;
        public static int shiftHorizontalTermoVertically;
        public static int shiftTheTermoVertikally ;
        public static int shiftTheTermoHorizontally;

        //1.28
        public static double kPhotoVertical;
        public static double kPhotoHorizontal;
        public static double kTermoHorizontal;
        public static double kTermoVertical;

        public static double anglePhotoVertical;
        public static double anglePhotoHorizontal;
        public static double angleTermoHorizontal;
        public static double angleTermoVertical;

        public static  String key;

        static {
//                try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(".").toAbsolutePath().getParent().resolve("program files").resolve("sizes.txt"))) {
                try  (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("D:\\NoosferaProgram\\program files\\sizes.txt"))){
                        String[] s = bufferedReader.readLine().split(" ");
                        kPhotoVertical = Double.parseDouble(s[0]);
                        kPhotoHorizontal = Double.parseDouble(s[1]);
                        kTermoHorizontal = Double.parseDouble(s[2]);
                        kTermoVertical = Double.parseDouble(s[3]);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                //try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(".").toAbsolutePath().getParent().resolve("program files").resolve("angles.txt"))) {
                try  (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("D:\\NoosferaProgram\\program files\\angles.txt"))){
                        String[] s = bufferedReader.readLine().split(" ");
                        anglePhotoVertical = Double.parseDouble(s[0]);
                        anglePhotoHorizontal = Double.parseDouble(s[1]);
                        angleTermoHorizontal = Double.parseDouble(s[2]);
                        angleTermoVertical = Double.parseDouble(s[3]);
                } catch (IOException e) {
                        e.printStackTrace();
                }

//                try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(".").toAbsolutePath().getParent().resolve("program files").resolve("shifts.txt"))){
                try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("D:\\NoosferaProgram\\program files\\shifts.txt"))){
                        String []s = bufferedReader.readLine().split(" ");
                        shiftHorizontalPhotoHorizontally = Integer.parseInt(s[0]);
                        shiftHorizontalPhotoVertically = Integer.parseInt(s[1]);
                        shiftThePhotoVertikally = Integer.parseInt(s[2]);
                        shiftThePhotoHorizontally = Integer.parseInt(s[3]);
                        shiftHorizontalTermoHorizontally = Integer.parseInt(s[4]);
                        shiftHorizontalTermoVertically = Integer.parseInt(s[5]);
                        shiftTheTermoVertikally = Integer.parseInt(s[6]);
                        shiftTheTermoHorizontally = Integer.parseInt(s[7]);
                } catch (IOException e) {
                        e.printStackTrace();
                }
//                try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(".").toAbsolutePath().getParent().resolve("program files").resolve("key.txt"))){
                try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("D:\\NoosferaProgram\\program files\\key.txt"))){
                        String []s = bufferedReader.readLine().split(" ");
                        key = s[0];
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public static int widthTermoVertical = (int) (330.0 * kTermoHorizontal);
        public static int heightTermoVertical = (int) (314.0 * kTermoHorizontal);
        public static int widthTermoHorizontal = (int) (330.0 * kTermoHorizontal);
        public static int heightTermoHorizontal = (int) (202.0 * kTermoHorizontal);

        public static int widthPhotoVertical = (int) (135.0 * kPhotoVertical);
        public static int heightPhotoVertical = (int) (180.0 * kPhotoVertical);
        public static int widthPhotoHorizontal = (int) (285.0 * kPhotoHorizontal);
        public static int heightPhotoHorizontal = (int) (213.0 * kPhotoHorizontal);

        public static final Path PATH_PICTURES = Path.of("D:\\NoosferaProgram\\pictures");
        public static final Path PATH_TERMO = Path.of("D:\\NoosferaProgram\\pictures\\Termo");
        public static final Path PATH_PHOTO = Path.of("D:\\NoosferaProgram\\pictures\\Photo");
        public static final Path PATH_PHOTO_HORIZONTAL = Path.of("D:\\NoosferaProgram\\pictures\\Photo\\Horizontal");
        public static final Path PATH_TO_SAVED_TERMO = Path.of("D:\\NoosferaProgram\\pictures\\ChangedTermo");
        public static final Path PATH_TO_SAVED_PHOTO = Path.of("D:\\NoosferaProgram\\pictures\\ChangedPhoto");
        public static final Path PATH_EXCEL_TEMP = Path.of("D:\\NoosferaProgram\\program files\\tempFile.xlsx");
        public static final Path PATH_CREATED_PROTOKOL = Path.of("D:\\NoosferaProgram\\Готовый отчет.xlsx");
        public static final Path PATH_HEADERS = Path.of("D:\\NoosferaProgram\\Содержание.xlsx");
        public static final Path PATH_TABLESPDF = Path.of("D:\\NoosferaProgram\\Новый отчет.pdf");
        public static final Path PATH_TABLESXLSX = Path.of("D:\\NoosferaProgram\\Новый отчет.xlsx");



        // NoosferaProgram
//        public static Path path = Paths.get(".").toAbsolutePath().getParent();
//        public static final Path PATH_PICTURES = path.resolve("pictures");
//        public static final Path PATH_TERMO = path.resolve("pictures").resolve("Termo");
//        public static final Path PATH_PHOTO = path.resolve("pictures").resolve("Photo");
//        public static final Path PATH_PHOTO_HORIZONTAL = path.resolve("pictures").resolve("Photo").resolve("Horizontal");
//        public static final Path PATH_TO_SAVED_TERMO = path.resolve("pictures").resolve("ChangedTermo");
//        public static final Path PATH_TO_SAVED_PHOTO = path.resolve("pictures").resolve("ChangedPhoto");
//        public static final Path PATH_EXCEL_TEMP = path.resolve("program files").resolve("tempFile.xlsx");
//        public static final Path PATH_CREATED_PROTOKOL = path.resolve("Готовый отчет.xlsx");
//        public static final Path PATH_HEADERS = path.resolve("Содержание.xlsx");
//        public static final Path PATH_TABLESPDF = path.resolve("Новый отчет.pdf");
//        public static final Path PATH_TABLESXLSX = path.resolve("Новый отчет.xlsx");




}
