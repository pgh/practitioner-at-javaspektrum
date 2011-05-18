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

import java.util.Collection;

/**
 * This class is the heart of the scanner.
 *
 * The enum ScanMode provides the signature for method #scan that's
 * implemented by every enum value individually.
 *
 * <br/>
 *
 * Each ScanMode value represents a possible mode of operation of
 * the scanner that initialized the processing state. Cause of the
 * statelessness of enum values the processing state like value
 * accumulator and list of values in the current line have to be
 * passed into the method #scan as arguments.
 *
 * <br/>
 *
 * #scan does the handling of the character to scan accordingly to
 * the current ScanMode and returns the resulting ScanMode.
 *
 * <br/>
 *
 * It's expected from someone else to take care of setup and keeping
 * processing state appropriately.
 *
 * @author ghadir
 * @since Mar 16, 2011 1:31:14 AM
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
