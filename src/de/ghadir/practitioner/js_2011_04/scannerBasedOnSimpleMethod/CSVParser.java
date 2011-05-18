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

package de.ghadir.practitioner.js_2011_04.scannerBasedOnSimpleMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ghadir
 * Date: Mar 25, 2011
 * Time: 4:48:17 PM
 */
public class CSVParser {


    public Collection<String> readline(String line) {

        boolean isQuoted = false;
        boolean isMasked = false;
        
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<String>();

        for (char ch : line.toCharArray()) {

            if ( isMasked ) {
                sb.append( ch );
                isMasked = false;
            } else if ( ch == '\\' ) {
                isMasked = true;
            } else if ( isQuoted && ch != '"' ) {
                sb.append( ch );
            } else if ( ch == '"' ) {
                isQuoted = !isQuoted;
            } else if ( ch == ';' ) {
                result.add( sb.toString() );
                sb.setLength(0);
            } else {
                sb.append( ch );
            }
        }
        result.add( sb.toString() );
        return result;
    }


    public static void main(String[] args) {
        System.out.println( new CSVParser().readline( "1;2;3;4" ) );
        System.out.println( new CSVParser().readline( "1;2\\\";3;4" ) );
        System.out.println( new CSVParser().readline( "1;\"2\\\";3\";4" ) );
        System.out.println( new CSVParser().readline( "\"1;\"2\\\";3\";4\"" ) );
        System.out.println( new CSVParser().readline( "\"1;2\\\";3;4\"" ) );
        System.out.println( new CSVParser().readline( "1;2\\\";3;4" ) );
        System.out.println( new CSVParser().readline( "1;\"2\";\"3;4\"" ) );
    }

}
