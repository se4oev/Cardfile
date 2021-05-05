package ru.spb.reshenie.javatasks.utils;

import javafx.scene.image.Image;

public class ImageUtil {

    private static final String iconsPath = '/' +
        ImageUtil.class.getPackage().getName().replace('.', '/') + '/';

    public static Image getFinSourceImage(Integer numOfIcon) {
        if (numOfIcon == 1) {
            return createImage("oms_icon.png");
        } else if (numOfIcon == 2) {
            return createImage("dms_icon.png");
        } else if (numOfIcon == 3) {
            return createImage("cash_icon.png");
        }
        return null;
    }

    private static Image createImage(String path) {
        path = iconsPath + path;
        return new Image(path);
    }

    public static Image cardfileImage = new Image(iconsPath + "cardfile_icon.png");


}
