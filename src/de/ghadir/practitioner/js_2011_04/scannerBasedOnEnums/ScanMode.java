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
 * @author Phillip Ghadir, phillip.ghadir@innoq.com
 * @since Mar 16, 2011 1:31:14 AM
 */
enum ScanMode {
    ReadingWord() {
        @Override
        public ScanMode scan(char ch, ScanningState scanningState) {
            switch ( ch ) {
                case '"': return Quoted;
                case '\\' : return Masked;
                case ';' :
                    scanningState.result.add( scanningState.sb.toString() );
                    scanningState.sb.setLength( 0 );
                    return ReadingWord;
                case '\n' :
                    scanningState.isLineComplete = true;
                    return LineEnd;
                default:
                    scanningState.sb.append( ch );
                    return ReadingWord;
            }
        }},
    LineEnd() {
        @Override
        public ScanMode scan(char ch, ScanningState scanningState) {
            if ( ch == '\n' || ch == '\r' ) {

                scanningState.isLineComplete = true;
                return LineEnd;
            } else {
                scanningState.isLineComplete = false;
                return ReadingWord.scan(ch, scanningState);
            }
        }
    },
    Masked() {
        @Override
        public ScanMode scan(char ch, ScanningState scanningState) {
            scanningState.sb.append( ch );
            return ReadingWord;
        }},
    Quoted() {
        @Override
        public ScanMode scan(char ch, ScanningState scanningState) {
            switch ( ch ) {
                case '"':
                    return ReadingWord;
                case '\\':
                    return MaskedInQuoted;
                default:
                    scanningState.sb.append( ch );
                    return Quoted;
            }
        }},
    MaskedInQuoted() {
        @Override
        public ScanMode scan(char ch, ScanningState scanningState) {
            scanningState.sb.append( ch );
            return Quoted;
        }};

    
    public abstract ScanMode scan(char ch, ScanningState scanningState);
}
