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

package de.ghadir.practitioner.js_2011_05.foundation.arithmetic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since 7/25/11 9:16 AM
 */
public class Parser {
    public Expression evaluate(final String formula) {
        // throw new UnsupportedOperationException(); // TODO

        return new Expression() {                    // TODO evaluate is not properly implemented...

            Collection<Symbol> symbols = new ArrayList<Symbol>();

            {
                for (String s : formula.split( "\\W+" ) ) {
                    symbols.add( new Symbol( s ) );
                }
            }

            @Override
            public Object evalInCtx(Binding env) {
                return null; // TODO
            }

            @Override
            public Collection<Symbol> getSymbols() {
                return symbols;
            }
        };
    }
}
