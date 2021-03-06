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

package de.ghadir.practitioner.js_2011_05.model.customer;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since 7/20/11 9:58 PM
 */
public enum CustomerAttributes {

    id( "ID", false ),
    firstName( "First Name" ),
    lastName( "Last Name" ),
    number( "Customer No", Integer.class ),
    incomePerMonth( "Monthly Income", Integer.class ),
    incomePerYear( "Annual Income", Integer.class );

    private String label;
    private boolean isEditable;
    private Class type;

    CustomerAttributes(String label) {
        this( label, true );
    }

    CustomerAttributes(String label, boolean editable) {
        this( label, editable, String.class );
    }

    CustomerAttributes(String label, Class type) {
        this( label, true, type );
    }

    CustomerAttributes(String label, boolean editable, Class type) {
        isEditable = editable;
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public Class getType() {
        return type;
    }
}
