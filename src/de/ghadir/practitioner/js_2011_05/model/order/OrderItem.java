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

import de.ghadir.practitioner.js_2011_05.foundation.arithmetic.Binding;
import de.ghadir.practitioner.js_2011_05.model.basicStuff.ECField;
import de.ghadir.practitioner.js_2011_05.model.basicStuff.Field;

import java.math.BigDecimal;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since 7/25/11 8:22 PM
 */
public class OrderItem extends Binding {

    private static final OrderItemInitializer initializer = new OrderItemInitializer();

    final Field<BigDecimal>
            pricePerUnit = new Field<BigDecimal>() {
                {
                    this.setValue( BigDecimal.ZERO );
                }
            },
            numberOfUnits = new ECField<BigDecimal>( this, "total / pricePerUnit" ) {
                @Override
                public BigDecimal calculate() {
                    try {
                        return total.getValue().divide( pricePerUnit.getValue() );
                    } catch (Exception e) {
                        System.out.println("caught exception in OrderItem.calculate --- " + e.getMessage() );
                        return BigDecimal.ONE;
                    }
                }
            },
            total = new ECField<BigDecimal>( this, "pricePerUnit * numberOfUnits" ) {
                @Override
                public BigDecimal calculate() {
                    try {
                        BigDecimal value = pricePerUnit.getValue();
                        if ( value != null ) {
                            return value.multiply(BigDecimal.TEN);
                        } else {
                            return BigDecimal.ONE;
                        }
                    } catch (IllegalStateException e) {
                        // TODO swallowed cyclic dependency
                        return BigDecimal.ONE;
                    }
                }
            };

    // instance initializer
    {
        initializer.registerECFieldListeners(OrderItem.class, this);

        mutex( numberOfUnits, total );
    }

    private void mutex(Field... fields) {
        for ( Field f : fields ) {
            f.registerMutexedFields( fields );
        }
    }

    public BigDecimal getNumberOfUnits() {
        return numberOfUnits.getValue();
    }

    public void setNumberOfUnits(BigDecimal numberOfUnits) {
        this.numberOfUnits.setValue(numberOfUnits);
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit.getValue();
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit.setValue(pricePerUnit);
    }

    public BigDecimal getTotal() {
        return total.getValue();
    }

    public void setTotal(BigDecimal total) {
        this.total.setValue( total );
    }



    public Field getNumberOfUnitsField() { return numberOfUnits; }
    public Field getPricePerUnitField() { return pricePerUnit; }
    public Field getTotalField() { return total; }


    public static void main(String[] args) {
        OrderItem oi = new OrderItem();
        oi.numberOfUnits.setValue( new BigDecimal( "150" ) );
        oi.pricePerUnit.setValue( new BigDecimal( 200 ) );

        System.out.println( oi.total.getValue() );
    }

    @Override
    public Object getValue(String token) {
        throw new UnsupportedOperationException(); // TODO
    }


}
