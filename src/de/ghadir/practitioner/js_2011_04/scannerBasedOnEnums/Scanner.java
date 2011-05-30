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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class provides the CSV parser.
 *
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since Mar 16, 2011 1:32:59 AM
 */
public class Scanner {


    public Iterator<Collection<String>> readCSV(final InputStream is) {
        return readCSV( new InputStreamReader( is ) );
    }

    public Iterator<Collection<String>> readCSV(final InputStreamReader is) {

        return new Iterator<Collection<String>>() {

            final char buffer[] = new char[8192];
            int offset = 0;
            int len = 0;
            boolean isClosed = false;

            public boolean hasNext() {

                if ( len > 0 && offset < len ) {
                    return true;
                } else {
                    if ( isClosed ) {
                        return false;
                    }
                    try {
                        offset = 0;
                        len = is.read( buffer );

                        if ( len <= 0 ) {
                            isClosed = true;
                            is.close();
                        }
                        return len > 0;
                    } catch (IOException e) {
                        throw new RuntimeException( e );
                    }
                }
            }

            public Collection<String> next() {
                ScanningState scanningState = new ScanningState();

                ScanMode s = ScanMode.ReadingWord;

                while ( !scanningState.isLineComplete() && hasNext() ) {
                    s = s.scan( buffer[offset++], scanningState );
                }

                return scanningState.getResult();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Collection<String> readline(String line) {

        ScanningState scanningState = new ScanningState();

        ScanMode s = ScanMode.ReadingWord;

        for ( char ch : line.toCharArray() ) {
            s = s.scan( ch, scanningState );
        }

        return scanningState.getResult();
    }


    public static void main(String[] args) {
        System.out.println( new Scanner().readline( "1;2;3;4" ) );
        System.out.println( new Scanner().readline( "1;2\\\";3;4" ) );
        System.out.println( new Scanner().readline( "1;\"2\\\";3\";4" ) );
        System.out.println( new Scanner().readline( "\"1;\"2\\\";3\";4\"" ) );
        System.out.println( new Scanner().readline( "\"1;2\\\";3;4\"" ) );
        System.out.println( new Scanner().readline( "1;2\\\";3;4" ) );
        System.out.println( new Scanner().readline( "1;2;;;3;4" ) );
        System.out.println( new Scanner().readline( "1;\"2\";\"3;4\"" ) );


        String testCSV = "abc;def;ghi\njkl;m\\\nno;opq;rst;\"abc\n\n\";jjjk";
        for ( Iterator i = new Scanner().readCSV(
                new ByteArrayInputStream( testCSV.getBytes() ) ); i.hasNext(); ) {
            System.out.println("i.next() = " + i.next());
        }
    }
}
