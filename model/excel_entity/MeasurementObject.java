package model.excel_entity;

import data.TempData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MeasurementObject {
    private static int countMeasurementObject = 0;
    private int objectNumber;
    private int numberOfBlocks;
    private String header;
    private List<MeasurementObjectBlock> measurementObjectBlockList = new ArrayList<>();


    public MeasurementObject(int numberOfBlocks) {
        this.header = TempData.headersQueue.poll();
        this.objectNumber = countMeasurementObject ++;
        this.numberOfBlocks = numberOfBlocks;
        measurementObjectBlockList.add(new MeasurementObjectBlock(true));

        for (int i  = 1; i < numberOfBlocks ; i++) {
            measurementObjectBlockList.add(new MeasurementObjectBlock(false));
        }


    }

}
