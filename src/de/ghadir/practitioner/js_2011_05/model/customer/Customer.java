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
