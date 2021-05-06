package ru.spb.reshenie.javatasks.ui;

import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import ru.spb.reshenie.javatasks.entity.Patient;


public class FullnameTableCell extends TableCell<Patient, String > {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
            setTooltip(null);
        } else {
            setText(fullnameFormat(item));
            this.setId("fio-cell");
            setTooltip(new Tooltip(item));
        }
    }

    private static String fullnameFormat(String fio) {
        String[] fullname = fio.split(" ");
        String result = fullname[0];
        if (fullname.length >= 3) {
            result += " " + fullname[1].charAt(0) + ". " + fullname[2].charAt(0) + ".";
        } else if (fullname.length == 2) {
            result += " " + fullname[1].charAt(0) + ".";
        }
        return result;
    }
}
