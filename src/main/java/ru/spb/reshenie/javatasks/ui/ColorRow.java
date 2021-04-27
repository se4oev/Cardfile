package ru.spb.reshenie.javatasks.ui;

import javafx.scene.control.TableRow;
import ru.spb.reshenie.javatasks.entity.PatientDTO;

public class ColorRow extends TableRow<PatientDTO> {
    @Override
    protected void updateItem(PatientDTO item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setStyle("");
        } else {
            if (item.getSex().equals("Мужской")) {
                setStyle("-fx-background-color: lightskyblue");
            } else {
                setStyle("-fx-background-color: lightcoral");
            }
        }
    }
}
