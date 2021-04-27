package ru.spb.reshenie.javatasks.ui;

import javafx.css.PseudoClass;
import javafx.scene.control.TableRow;
import javafx.scene.paint.Color;
import ru.spb.reshenie.javatasks.entity.PatientDTO;

public class ColorRow extends TableRow<PatientDTO> {

    @Override
    protected void updateItem(PatientDTO item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setStyle("");
        } else {
            if (item.getSex().equals("Мужской")) {
                setStyle("-fx-background-color: rgba(135, 206, 250, 0.2)");
            } else {
                setStyle("-fx-background-color: rgba(240, 128, 128, 0.2)");
            }
        }
    }
    @Override
    public void updateSelected(boolean selected) {
        super.updateSelected(selected);
        if(isSelected()) {
            setStyle("-fx-background-color: rgba(169, 169, 169, 0.8)");
        } else {
            getTableView().refresh();
        }
    }
}
