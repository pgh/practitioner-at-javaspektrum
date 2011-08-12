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

package de.ghadir.practitioner.js_2011_05.model.order;

import de.ghadir.practitioner.js_2011_05.foundation.arithmetic.Symbol;
import de.ghadir.practitioner.js_2011_05.model.basicStuff.ECField;
import de.ghadir.practitioner.js_2011_05.model.basicStuff.Field;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since 7/25/11 8:33 PM
 */
class OrderItemInitializer {

    void registerECFieldListeners(Class<OrderItem> theClass, Object theInstance ) {
        for (Field<BigDecimal> field : getFields( theClass, theInstance ) ) {
            if (field instanceof ECField) {
                ECField ecField = (ECField) field;

                for ( Object s : ecField.getReferencedSymbols() ) { // TODO assignment to object

                    Field<BigDecimal> supplier = getField(theClass, theInstance, (Symbol) s); // TODO stupid cast
                    if ( supplier != null ) {
                        supplier.registerListener(field);
                    }
                }

                /*
                // TODO
                for ( String s : ecField.mutexNotifications ) {
                    Field<BigDecimal> supplier = getField(theClass, theInstance, s);
                    if ( supplier != null ) {
                        supplier.registerListenerFor(field);
                    }

                }
                */
            }
        }
    }

    private Collection<Field<BigDecimal>> getFields( Class<OrderItem> theClass, Object theInstance ) {

        try {
            Collection<Field<BigDecimal>> result = new ArrayList<Field<BigDecimal>>();
            for (java.lang.reflect.Field f : theClass.getDeclaredFields()) {
                f.setAccessible(true);

                if (Field.class.isAssignableFrom(f.getType())) {
                    System.err.println( f );
                    result.add((Field<BigDecimal>) f.get( theInstance ));
                }
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Illegal access in '" + theInstance + "'", e);
        }
    }

    private Field<BigDecimal> getField( Class<OrderItem> theClass, Object theInstance, Symbol s) {
        return getField(theClass, theInstance, s.getToken());
    }


    private Field<BigDecimal> getField( Class<OrderItem> theClass, Object theInstance, String fieldname) {
        try {
            java.lang.reflect.Field field = theClass.getDeclaredField(fieldname);
            field.setAccessible(true);

            return (Field<BigDecimal>) field.get( theInstance );
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Row does not contain a variable '" + fieldname + "'", e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Illegal access in Row '" + theInstance + "' to variable '" + fieldname + "'", e);
        }
    }


}
