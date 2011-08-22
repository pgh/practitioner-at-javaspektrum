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

import de.ghadir.practitioner.js_2011_05.basicUIStuff.FieldCellEditor;
import de.ghadir.practitioner.js_2011_05.basicUIStuff.FieldCellRenderer;
import de.ghadir.practitioner.js_2011_05.model.basicStuff.Field;
import de.ghadir.practitioner.js_2011_05.model.order.OrderItem;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since 7/26/11 4:57 PM
 */
public class OrderItemTable extends JTable {

    public OrderItemTable() {

        super();

        setShowGrid( true );
        setRowSelectionAllowed( false );
        setShowHorizontalLines( true );
        setShowVerticalLines( true );
        setGridColor( Color.GRAY );
        setFocusable( true );
        setFillsViewportHeight( true );

        setDefaultEditor( Field.class, new FieldCellEditor() );
        setDefaultRenderer( Field.class, new FieldCellRenderer() );

        setModel( new OrderItemModel() );
    }

    private class OrderItemModel implements TableModel {

        public final String columns[] = { "Number of Units", "Price per Unit", "Total Price" };

        java.util.List<OrderItem> orderItems = new ArrayList<OrderItem>();

        {
            for (int i = 0; i < 10; i++) {
                orderItems.add( new OrderItem() );
            }
        }

        public int getRowCount() {
            return orderItems.size();
        }

        public int getColumnCount() {
            return columns.length;
        }

        public String getColumnName(int columnIndex) {

            return columns[ columnIndex ];
        }

        public Class<?> getColumnClass(int columnIndex) {
            return Field.class;
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        public Field<BigDecimal> getValueAt(int rowIndex, int columnIndex) {
            OrderItem item = orderItems.get(rowIndex);

            switch ( columnIndex ) {
                case 0  : return item.getNumberOfUnitsField();
                case 1  : return item.getPricePerUnitField();
                case 2  : return item.getTotalField();
            }
            throw new RuntimeException(); // return null;
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            System.out.println( "OrderItemTable$OrderItemModel.setValueAt ---  " + aValue.getClass() );

            BigDecimal v = null;

            if ( aValue instanceof String ) {
                try {
                    v = new BigDecimal( (String) aValue );
                } catch (Exception e) {
                    System.out.println("caught exception " + e.getMessage() + " --- with value " + v);
                }
            } else {
                throw new IllegalArgumentException();
            }

            getValueAt(rowIndex, columnIndex).setValue( v );
        }

        public void addTableModelListener(TableModelListener l) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void removeTableModelListener(TableModelListener l) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

}
