package data;

import model.excel_entity.MeasurementObject;
import model.excel_entity.MeasurementObjectBlock;
import utils.HeadersCreator;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TempData {
    public  static Queue<Path> pathListTermo = new LinkedList<>();
    public  static Queue<Path> pathListPhoto = new LinkedList<>();

    public static List<MeasurementObject> measurementObjectList = new ArrayList<>();


    public static Queue<String> headersQueue = new LinkedList<>();
    public static Queue<String> thermogramHotSpots = new LinkedList<>();
    public static Queue<String> thermogramColdSpots = new LinkedList<>();
    public static Queue<String> thermogramCenterSpots = new LinkedList<>();

    public static int rowUp = -46;
    public static int rowLow = -21;
    public static int colTermo = 0;
    public static int colPhoto = 6;
}
