package de.ghadir.practitioner.js_2011_05.ui;

import de.ghadir.practitioner.js_2011_05.model.customer.Customer;
import de.ghadir.practitioner.js_2011_05.model.customer.CustomerAttributes;

import javax.swing.table.AbstractTableModel;

/**
 * @author ghadir
 * since 7/24/11 8:18 PM
 */
public class CustomerTableModel extends AbstractTableModel {

    private Customer customers[] = new Customer[15];


    {
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer();
        }
    }


    public int getRowCount() {
        return customers.length;
    }

    public int getColumnCount() {
        return CustomerAttributes.values().length;
    }

    public String getColumnName(int i) {
        return CustomerAttributes.values()[i].getLabel();
    }

    public Class<?> getColumnClass(int i) {
        return CustomerAttributes.values()[i].getType();
    }

    public boolean isCellEditable(int row, int col) {
        return CustomerAttributes.values()[col].isEditable();
    }

    public Object getValueAt(int row, int col) {
        return customers[row].getAttribute(CustomerAttributes.values()[col]);
    }

    public void setValueAt(Object o, int row, int col) {
        customers[row].setAttribute(CustomerAttributes.values()[col], o);
        fireTableCellUpdated(row, col);
    }
}

