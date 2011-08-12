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

import de.ghadir.practitioner.js_2011_05.model.customer.Customer;
import de.ghadir.practitioner.js_2011_05.model.customer.CustomerAttributes;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * since 7/24/11 8:18 PM
 */
public class CustomerTableModel extends AbstractTableModel {

    private Customer customers[];

    {
        List<Customer> result = new ArrayList<Customer>();

        int id = 1000;
        for ( String lastname : Arrays.asList( "Meier", "MŸller", "Schulze" ) ) {
            for ( String firstname : Arrays.asList( "Harry", "Ron", "Hermine", "Fred", "George" ) ) {
                Customer customer = new Customer();
                customer.setAttribute( CustomerAttributes.id, "" + ++id );
                customer.setAttribute( CustomerAttributes.firstName,  firstname );
                customer.setAttribute( CustomerAttributes.lastName, lastname );

                result.add(customer);
            }
        }
        customers = result.toArray( new Customer[ result.size() ] );
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

