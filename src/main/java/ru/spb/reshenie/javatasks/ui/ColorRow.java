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
                this.setId("man");
            } else {
                this.setId("woman");
            }
        }
    }
    @Override
    public void updateSelected(boolean selected) {
        super.updateSelected(selected);
        if(isSelected()) {
            this.setId("selected-row");
        } else {
            getTableView().refresh();
        }
    }
}
