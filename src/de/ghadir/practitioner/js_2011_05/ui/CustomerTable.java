/*
 *   Copyright 2011 Phillip Ghadir
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *   either express or implied. See the License for the specific
 *   language governing permissions and limitations under the License.
 */

package de.ghadir.practitioner.js_2011_05.ui;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
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
