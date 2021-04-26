package ru.spb.reshenie.javatasks.ui;

import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import ru.spb.reshenie.javatasks.entity.PatientDTO;
import ru.spb.reshenie.javatasks.utils.ImageUtil;

public class ImageTableCell extends TableCell<PatientDTO, Integer> {

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setGraphic(null);
            setTooltip(null);
        } else {
            ImageView image = new ImageView(ImageUtil.getFinSourceImage(item));
            setGraphic(image);
            if (item == 1) {
                setTooltip(new Tooltip("ОМС"));
            } else if (item == 2) {
                setTooltip(new Tooltip("ДМС"));
            } else if (item == 3) {
                setTooltip(new Tooltip("Наличные"));
            }

        }
    }
}
