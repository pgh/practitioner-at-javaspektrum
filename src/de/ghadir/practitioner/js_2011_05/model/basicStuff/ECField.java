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

package de.ghadir.practitioner.js_2011_05.model.basicStuff;

import de.ghadir.practitioner.js_2011_05.foundation.arithmetic.Binding;
import de.ghadir.practitioner.js_2011_05.foundation.arithmetic.Expression;
import de.ghadir.practitioner.js_2011_05.foundation.arithmetic.Parser;
import de.ghadir.practitioner.js_2011_05.foundation.arithmetic.Symbol;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class encapsulates the feature for storing user input as well as
 * computing the value via a given formula.
 *
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since 7/25/11 9:05 AM
 */
public class ECField<T extends Comparable> extends Field<T> {

    private static Parser parser = new Parser();

    Binding env;
    Expression<T> expr;
    private boolean isCalculating = false;
    private T calculated;

    public ECField(Binding env, String formula) {
        this.env = env;
        this.expr = parser.evaluate( formula );
    }

    @Override
    public T getValue() {
        if ( isCalculating ) {
            throw new IllegalStateException( "cyclic dependency!" );
        }

        T temp = super.getValue();
        if ( temp != null )
            return temp;
        else
            return getCalculated();
    }

    @Override
    public void setValue(T input) {
        internalSetValue( input );
        this.calculated = null;

        notifyListeners();
    }

    protected void setCalculated(T calculated) {
        this.calculated = calculated;
        internalSetValue( null );

        notifyListeners();
    }

    protected final T doCalculate() {
        try {
            isCalculating = true;
            setCalculated(calculate());

            return getCalculated();
        } finally {
            isCalculating = false;
        }
    }

    public T getCalculated() {
        if ( calculated == null ) {
            calculated = doCalculate();
        }
        return calculated;
    }


    @Override
    public void notifyChange(Field source) {
        clearCalculation();
    }

    private void clearCalculation() {
        calculated = null;
    }


    public T calculate() {
        if (expr != null)
            return expr.evalInCtx(env);
        return null;
    }

    public Collection<Symbol> getReferencedSymbols() {
        if ( expr != null ) {
            return expr.getSymbols();
        }
        return new ArrayList<Symbol>();
    }

    public boolean internalIsCalculated() {
        return calculated != null;
    }
}