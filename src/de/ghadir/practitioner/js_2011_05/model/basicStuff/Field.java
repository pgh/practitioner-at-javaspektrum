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

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since 7/25/11 9:02 AM
 */
public class Field<T extends Comparable> implements FieldListener {
    private T value;
    private Collection<FieldListener> listeners = new ArrayList<FieldListener>();

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        internalSetValue( value );
        notifyListeners();
    }

    public void registerListener(FieldListener client) {
        listeners.add( client );
    }

    protected void notifyListeners() {
        for ( FieldListener listener : listeners ) {
            listener.notifyChange(this);
        }
    }


    public void notifyChange(Field source) {
    }

    protected void internalSetValue(T value) {
        this.value = value;
    }
}
