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

package de.ghadir.practitioner.js_2011_04.scannerBasedOnEnums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ghadir
 * Date: Mar 18, 2011
 * Time: 9:30:53 PM
 */
class ScanningState {

    StringBuilder sb = new StringBuilder();
    List<String> result = new ArrayList<String>();

    public Collection<String> getResult() {
        result.add( sb.toString() );
        sb.setLength(0);
        
        return result;
    }
}
