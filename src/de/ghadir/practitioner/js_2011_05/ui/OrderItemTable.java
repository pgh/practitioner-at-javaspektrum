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

import de.ghadir.practitioner.js_2011_05.model.basicStuff.ECField;
import de.ghadir.practitioner.js_2011_05.model.basicStuff.Field;
import de.ghadir.practitioner.js_2011_05.model.order.OrderItem;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;

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

        setDefaultEditor( Field.class, new InnerCellEditor() );

        setModel( new OrderItemModel() );


        for ( Enumeration<TableColumn> columns = getColumnModel().getColumns(); columns.hasMoreElements(); ) {
            TableColumn tc = columns.nextElement();

            tc.setCellRenderer( new InnerCellRenderer() );
        }
    }


    private class InnerCellRenderer extends JLabel implements TableCellRenderer {

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

    private class InnerCellEditor extends DefaultCellEditor {

        private InnerCellEditor() {
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
            System.out.println("OrderItemTable$InnerCellEditor.getCellEditorValue --- cellEditorValue = " + cellEditorValue + " - " + cellEditorValue.getClass()  );
            return cellEditorValue;
        }
    }
}
