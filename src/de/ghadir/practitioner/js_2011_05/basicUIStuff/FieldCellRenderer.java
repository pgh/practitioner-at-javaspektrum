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

package de.ghadir.practitioner.js_2011_05.basicUIStuff;

import de.ghadir.practitioner.js_2011_05.model.basicStuff.ECField;
import de.ghadir.practitioner.js_2011_05.model.basicStuff.Field;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since 7/25/11 1:40 AM
 */
public class FieldCellRenderer extends JLabel implements TableCellRenderer {

    {
        setHorizontalAlignment( JLabel.RIGHT );
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Field valueAt = (Field) table.getValueAt(row, column);

        String label = "" + valueAt.getValue();
        setForeground( Color.BLUE );
        setToolTipText( "Entered value" );

        if ( valueAt instanceof ECField && ((ECField) valueAt).internalIsCalculated() ) {
            setForeground( Color.BLACK );
            setToolTipText( "Calculated value" );
        }

        setText( label );

        return this;
    }
}

