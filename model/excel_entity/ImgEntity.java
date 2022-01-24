package model.excel_entity;

import constants.Constants;
import data.TempData;
import lombok.Data;
import model.img.Type;
import utils.Util;

@Data
public class ImgEntity {
    boolean isUp;
    int col;
    int row;
    String pathImg;
    Type type;

    public ImgEntity(boolean isUp, String pathImg) {
        this.isUp = isUp;
        this.pathImg = pathImg;
        this.type = Util.determineType(pathImg);
        setPosition();
    }

    private void setPosition(){
        if (type==Type.PHOTO_VERTICAL){
            //0
            col = TempData.colPhoto+ Constants.shiftThePhotoHorizontally;
            setRow();
            this.row += Constants.shiftThePhotoVertikally;

        }if (type==Type.PHOTO_HORIZONTAL){
            col = TempData.colPhoto + Constants.shiftHorizontalPhotoHorizontally;
            setRow();
            this.row += Constants.shiftHorizontalPhotoVertically;

        }if (type==Type.THERMO_VERTICAL){
            col = TempData.colTermo + Constants.shiftTheTermoHorizontally;
            setRow();
            this.row += Constants.shiftTheTermoVertikally;

        }if (type==Type.THERMO_HORIZONTAL){
            col = TempData.colTermo + Constants.shiftHorizontalTermoHorizontally;
            setRow();
            this.row+= Constants.shiftHorizontalTermoVertically;
        }
    }
    private void setRow(){
        if (this.isUp) {
            this.row = TempData.rowUp;
        }else {
            this.row = TempData.rowLow;
        }
    }

}
