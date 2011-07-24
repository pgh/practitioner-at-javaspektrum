package de.ghadir.practitioner.js_2011_05.ui;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * @author ghadir
 * @since 7/20/11 9:54 PM
 */
public class CustomerTable extends JTable {

    public CustomerTable() {

        super(new CustomerTableModel());

        setShowGrid( true );
        setRowSelectionAllowed( false );
        setShowHorizontalLines( true );
        setShowVerticalLines( true );
        setGridColor( Color.GRAY );
        setEditingColumn( 1 );
        setEditingRow( 1 );
        setFocusable( true );
        setFillsViewportHeight( true );

        updateTableColumnModel();
    }

    private void updateTableColumnModel() {
        int preferredWidths[] = new int[] { 45, 200, 200, 40 };

        TableColumnModel columnModel1 = this.getColumnModel();
        for (int i = 0; i < getColumnCount(); i++) {
            columnModel1.getColumn(i).setWidth( preferredWidths[i] );
            columnModel1.getColumn(i).setPreferredWidth(preferredWidths[i]);
        }
    }
}
