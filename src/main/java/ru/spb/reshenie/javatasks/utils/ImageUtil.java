package ru.spb.reshenie.javatasks.utils;

import javafx.scene.image.Image;

public class ImageUtil {

    private static final String iconsBasePath = '/' +
        ImageUtil.class.getPackage().getName().replace('.', '/') + '/';
    private static final String omsIconPath = "oms_icon.png";
    private static final String dmsIconPath = "dms_icon.png";
    private static final String cashIconPath = "cash_icon.png";
    private static final String cardfileIconPath = "cardfile_icon.png";


    public static Image getFinSourceImage(Integer numOfIcon) {
        if (numOfIcon == 1) {
            return createImage(omsIconPath);
        } else if (numOfIcon == 2) {
            return createImage(dmsIconPath);
        } else {
            return createImage(cashIconPath);
        }
    }

    private static Image createImage(String path) {
        path = iconsBasePath + path;
        return new Image(path);
    }

    public static Image cardfileImage = new Image(iconsBasePath + cardfileIconPath);


}
