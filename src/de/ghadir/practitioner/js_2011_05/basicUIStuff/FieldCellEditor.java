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

import de.ghadir.practitioner.js_2011_05.model.basicStuff.Field;

import javax.swing.*;
import java.awt.*;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since 8/22/11 11:02 PM
 */
public class FieldCellEditor extends DefaultCellEditor {

    public FieldCellEditor() {
        super(new JTextField());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        ((JTextField) this.editorComponent).setText("" + ((Field) table.getValueAt( row, column )).getValue());

        return this.editorComponent;
    }

    @Override
    public Object getCellEditorValue() {
        Object cellEditorValue = super.getCellEditorValue();
        System.out.println("FieldCellEditor.getCellEditorValue --- cellEditorValue = " + cellEditorValue + " - " + cellEditorValue.getClass()  );
        return cellEditorValue;
    }
}
