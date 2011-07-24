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

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author ghadir
 * @since 7/20/11 10:02 PM
 */
public class Customer {

    SortedMap<CustomerAttributes, Object> attributes = new TreeMap<CustomerAttributes, Object>();

    public Object getAttribute(CustomerAttributes attr) {
        return attributes.get( attr );
    }

    public void setAttribute(CustomerAttributes attr, Object o) {
        if ( o != null && ! attr.getType().isAssignableFrom( o.getClass() ) ) {
            throw new IllegalArgumentException(
                    "Object " + o + " is not assignable to Attribute '" + attr.name() + "'" );
        }

        this.attributes.put( attr, o );
    }
}
