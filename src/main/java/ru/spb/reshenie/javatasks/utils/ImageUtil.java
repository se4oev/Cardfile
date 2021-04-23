package ru.spb.reshenie.javatasks.utils;

import javafx.scene.image.Image;

public class ImageUtil {
//    public static Image cashImage = new Image("ru/spb/reshenie/javatasks/utils/cash_icon.png");
//    public static Image cardfileImage = new Image("ru/spb/reshenie/javatasks/utils/cardfile_icon.png");

    public static Image cashImage = new Image('/' + ImageUtil.class.getPackage().getName().replace('.', '/') +'/' + "cash_icon.png");
    public static Image cardfileImage = new Image('/' + ImageUtil.class.getPackage().getName().replace('.', '/') +'/' + "cardfile_icon.png");


}
