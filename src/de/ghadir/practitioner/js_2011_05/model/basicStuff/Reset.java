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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since 8/18/11 4:00 PM
 */
public class Reset implements MutexListener {
    private Collection<MutexListener> mutexedFields = new HashSet<MutexListener>();

    Object owner;


    public Reset(Object owner, MutexListener... mutexedFields) {
        this( owner, Arrays.<MutexListener>asList(mutexedFields));
    }


    public Reset(Object owner, Collection<MutexListener> mutexedFields) {
        this.owner = owner;
        this.mutexedFields.addAll( mutexedFields );
    }

    protected void notifyListeners() {
        for ( MutexListener listener : mutexedFields ) {
            listener.notifyChange(this);
        }
    }

    public boolean notifyChange(Reset source) {
        return false;
    }

    public void registerListeners(Field[] fields) {
        for ( Field f : fields ) {

            if ( f != this.owner ) {
                mutexedFields.add( f );
            }
        }
    }
}
