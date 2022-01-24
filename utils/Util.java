package utils;

import constants.Constants;
import data.TempData;
import model.excel_entity.MeasurementObjectBlock;
import model.img.Img;
import model.img.Type;

import java.util.List;

public class Util {


    public static int determineWidth(Img img){

        if (img.getBufferedImageInput().getWidth() == 1050) return Constants.widthTermoVertical;
        if (img.getBufferedImageInput().getWidth() == 1633) return Constants.widthTermoHorizontal;
        return 0;
    }
    public static int determineHeight(Img img){
        int height = img.getBufferedImageInput().getHeight();
        int width = img.getBufferedImageInput().getWidth();

        if (height == 1000 && width == 1050){
            img.setType(Type.THERMO_VERTICAL);
            return Constants.heightTermoVertical;
        }
        if (height == 1000 && width == 1633){
            img.setType(Type.THERMO_HORIZONTAL);
            return Constants.heightTermoHorizontal;
        }
        return 0;
    }

    public static String generateName(Img img){
        StringBuilder name = new StringBuilder();
        name.append(img.getPathToWrite().toString()).append("\\").append(img.getFileName()).delete(name.length()-4,name.length());
        switch (img.getType()){
            case PHOTO_VERTICAL: name.append("_P_V");break;
            case PHOTO_HORIZONTAL: name.append("_P_H");break;
            case THERMO_VERTICAL: name.append("_T_V");break;
            case THERMO_HORIZONTAL: name.append("_T_H");break;
        }
        return name.append(".jpg").toString();
    }

    public static Type determineType(String s){
       String s1 =   s.substring(s.length()-7);
       Type t = null;
       switch (s1){
           case "T_V.jpg" : t = Type.THERMO_VERTICAL;break;
           case "T_H.jpg" : t = Type.THERMO_HORIZONTAL;break;
           case "P_V.jpg" : t = Type.PHOTO_VERTICAL;break;
           case "P_H.jpg" : t = Type.PHOTO_HORIZONTAL;break;
       }
       return t;
    }
}
