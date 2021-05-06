package ru.spb.reshenie.javatasks.ui;

import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.utils.ImageUtil;

public class ImageTableCell extends TableCell<Patient, Integer> {

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setGraphic(null);
            setTooltip(null);
        } else if (item >= 1 && item <= 3) {
            ImageView image = new ImageView(ImageUtil.getFinSourceImage(item));
            setGraphic(image);
            if (item == 1) {
                setTooltip(new Tooltip("ОМС"));
            } else if (item == 2) {
                setTooltip(new Tooltip("ДМС"));
            } else {
                setTooltip(new Tooltip("Наличные"));
            }
        }
    }
}
