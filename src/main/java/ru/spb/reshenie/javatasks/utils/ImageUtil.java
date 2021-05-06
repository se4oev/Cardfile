package ru.spb.reshenie.javatasks.utils;

import javafx.scene.image.Image;

public class ImageUtil {

    private static final String ICONS_BASE_PATH = '/' +
        ImageUtil.class.getPackage().getName().replace('.', '/') + '/';
    private static final String OMS_ICON_PNG = "oms_icon.png";
    private static final String DMS_ICON_PNG = "dms_icon.png";
    private static final String CASH_ICON_PNG = "cash_icon.png";
    private static final String CARDFILE_ICON_PNG = "cardfile_icon.png";


    public static Image getFinSourceImage(Integer numOfIcon) {
        if (numOfIcon == 1) {
            return createImage(OMS_ICON_PNG);
        } else if (numOfIcon == 2) {
            return createImage(DMS_ICON_PNG);
        } else {
            return createImage(CASH_ICON_PNG);
        }
    }

    private static Image createImage(String path) {
        path = ICONS_BASE_PATH + path;
        return new Image(path);
    }

    public static Image cardfileImage = new Image(ICONS_BASE_PATH + CARDFILE_ICON_PNG);


}
