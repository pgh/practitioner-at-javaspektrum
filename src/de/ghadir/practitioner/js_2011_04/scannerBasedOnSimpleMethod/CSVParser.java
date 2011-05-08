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
