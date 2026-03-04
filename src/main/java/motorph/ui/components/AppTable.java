package motorph.ui.components;

import javax.swing.*;
import javax.swing.table.TableModel;

public class AppTable extends JTable {

    public AppTable(TableModel model) {
        super(model);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
