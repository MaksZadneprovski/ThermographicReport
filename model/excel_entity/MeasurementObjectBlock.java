package model.excel_entity;

import data.TempData;
import lombok.Data;

@Data
public class MeasurementObjectBlock {
    private static int countMeasurementObjectBlock = 0;

    private int blockNumber;
    private boolean isUpBlock;
    private boolean isExistPhoto;
    private ImgEntity photo;
    private ImgEntity termo;
    private int position;

    private String maxTemperature;
    private String midTemperature;
    private String lowTemperature;


    public MeasurementObjectBlock(boolean isExistPhoto) {
        this.maxTemperature = TempData.thermogramHotSpots.poll();
        this.midTemperature = TempData.thermogramCenterSpots.poll();
        this.lowTemperature = TempData.thermogramColdSpots.poll();
        this.blockNumber = countMeasurementObjectBlock ++;

        if (blockNumber % 2 == 0){
            isUpBlock = true;
            TempData.rowUp+=50;
            this.position = TempData.rowUp;
        }else {
            isUpBlock = false;
            TempData.rowLow += 50;
            this.position = TempData.rowLow;
        }

        this.isExistPhoto = isExistPhoto;

        if (!TempData.pathListTermo.isEmpty()) {
            this.termo = new ImgEntity(isUpBlock, TempData.pathListTermo.poll().toString());
        }
        if(isExistPhoto){

            if (!TempData.pathListPhoto.isEmpty()) {
                this.photo = new ImgEntity(isUpBlock, TempData.pathListPhoto.poll().toString());
            }

            if (!TempData.pathListTermo.isEmpty()) {
                this.termo = new ImgEntity(isUpBlock, TempData.pathListTermo.poll().toString());
            }
        }



    }



}
