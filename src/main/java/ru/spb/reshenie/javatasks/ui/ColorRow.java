package ru.spb.reshenie.javatasks.ui;

import javafx.scene.control.TableRow;
import ru.spb.reshenie.javatasks.entity.Patient;

public class ColorRow extends TableRow<Patient> {

    @Override
    protected void updateItem(Patient item, boolean empty) {
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
            setStyle("-fx-background-color: rgba(33, 165, 209, 1.0)");
        } else {
            getTableView().refresh();
        }
    }
}
