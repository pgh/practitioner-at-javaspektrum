package de.ghadir.practitioner.js_2011_04.scannerBasedOnEnums;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: ghadir
 * Date: Mar 16, 2011
 * Time: 1:31:14 AM
 */
enum ScanMode {
    ReadingWord() {
        @Override
        protected ScanMode scan(char ch, StringBuilder sb, Collection<String> result) {
            switch ( ch ) {
                case '"': return Quoted;
                case '\\' : return Masked;
                case ';' :
                    result.add( sb.toString() );
                    sb.setLength( 0 );
                    return ReadingWord;
                default:
                    sb.append( ch );
                    return ReadingWord;
            }
        }},
    Masked() {
        @Override
        protected ScanMode scan(char ch, StringBuilder sb, Collection<String> result) {
            sb.append( ch );
            return ReadingWord;
        }},
    Quoted() {
        @Override
        protected ScanMode scan(char ch, StringBuilder sb, Collection<String> result) {
            switch ( ch ) {
                case '"':
                    return ReadingWord;
                case '\\':
                    return MaskedInQuoted;
                default:
                    sb.append( ch );
                    return Quoted;
            }
        }},
    MaskedInQuoted() {
        @Override
        protected ScanMode scan(char ch, StringBuilder sb, Collection<String> result) {
            sb.append( ch );
            return Quoted;
        }};

    
    public ScanMode scan(char ch, ScanningState scanningState) {
        return this.scan( ch, scanningState.sb, scanningState.result );
    }

    protected abstract ScanMode scan(char ch, StringBuilder sb, Collection<String> result);
}
