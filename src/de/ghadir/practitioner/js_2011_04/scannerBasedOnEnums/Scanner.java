package de.ghadir.practitioner.js_2011_04.scannerBasedOnEnums;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: ghadir
 * Date: Mar 16, 2011
 * Time: 1:32:59 AM
 */
public class Scanner {

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
    }
}
