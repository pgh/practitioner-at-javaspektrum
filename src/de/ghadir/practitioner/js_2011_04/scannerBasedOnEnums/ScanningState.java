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
