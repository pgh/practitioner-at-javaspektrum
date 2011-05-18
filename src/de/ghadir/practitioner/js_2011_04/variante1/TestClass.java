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

package de.ghadir.practitioner.js_2011_04.variante1;

import de.ghadir.practitioner.js_2011_04.scannerBasedOnEnums.Scanner;
import de.ghadir.practitioner.js_2011_04.scannerBasedOnSimpleMethod.CSVParser;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ghadir
 * Date: Mar 26, 2011
 * Time: 12:53:20 AM
 */
public class TestClass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        CSVParser csvParser = new CSVParser();


        List<String> list = Arrays.asList(
         "1;2;3;4",
         "1;2\\\";3;4",
         "1;\"2\\\";3\";4",
         "\"1;\"2\\\";3\";4\"",
         "\"1;2\\\";3;4\"",
         "1;2\\\";3;4",
         "1;2;;;3;4",
         "1;\"2\";\"3;4\"" );


        for ( String s : list ) {
            if ( !isEqual( scanner.readline( s ), csvParser.readline( s ) ) ) {
                throw new RuntimeException( s + "\n" + scanner.readline( s ) + "\n" + csvParser.readline( s ) );
            }
        }

        System.out.println("check complete");
    }

    private static boolean isEqual( Collection l1, Collection l2 ) {
        if ( l1 == l2 ) {
            return true;
        }

        if ( l1 == null || l2 == null ) {
            return false;
        }

        if ( l1.size() != l2.size() ) {
            return false;
        }

        Iterator i1 = l1.iterator();
        Iterator i2 = l2.iterator();

        while ( i1.hasNext() && i2.hasNext() ) {
            if ( ! i1.next().equals( i2.next() ) ) {
                return false;
            }
        }
        
        return true;
    }
}
