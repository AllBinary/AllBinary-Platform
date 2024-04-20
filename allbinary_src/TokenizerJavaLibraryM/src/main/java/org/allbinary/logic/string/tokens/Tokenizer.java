/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
 */
package org.allbinary.logic.string.tokens;

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.StringUtil;

public class Tokenizer {

    private String sep;
    private String endSep;

    public Tokenizer(String sep) throws Exception {
        if (sep == null || sep.compareTo(StringUtil.getInstance().EMPTY_STRING) == 0) {
            throw new Exception("Sep provided is not valid");
        }
        this.sep = sep;
        this.endSep = endSep;
    }

    public void setSep(String sep) {
        this.sep = sep;
    }

    public void setEndSep(String endSep) {
        this.endSep = endSep;
    }

    public BasicArrayList getInsideSep(String string) {
        final BasicArrayList tokenList = new BasicArrayList();
        int index = 0;
        int size = string.length();
        while (index < size) {
            index = string.indexOf(sep, index);
            if (index != -1) {
                //found possible beginning of token
                int end = string.indexOf(endSep, index + sep.length());
                if (end != -1) {
                    tokenList.add(string.substring(index + sep.length(), end - (endSep.length() - 1)));
                    index = end + endSep.length();
                } else {
                    //break;
                }
            } else {
                //The end of the string was found
                break;
            }
        }
        return tokenList;
    }

    //BasicArrayList tokenVector = new BasicArrayList();
    public BasicArrayList getTokens(String string, BasicArrayList tokenVector) {
        int index = 0;
        int end = 0;
        while (index < string.length()) {
            end = string.indexOf(sep, index);
            // found next token
            if (end != -1) {
                tokenVector.add(string.substring(index, end));
                index = end + sep.length();
            } else {
                tokenVector.add(string.substring(index, string.length()));
                break;
            }
        }
        return tokenVector;
    }

    //take a vector of strings is accepted and each string is broken in two by the specified seperator
    //and added to a hashmap
    public Hashtable getTokens(BasicArrayList stringVector) {
        Hashtable tokenHashtable = new Hashtable();

        String string = StringUtil.getInstance().EMPTY_STRING;
        int end = 0;

        int size = stringVector.size();

        for (int index = 0; index < size; index++) {
            string = (String) stringVector.objectArray[index];
            end = string.indexOf(sep);

            //found next token
            if (end >= 0) {
                tokenHashtable.put(
                    string.substring(0, end),
                    string.substring(end + 1, string.length()));
            }
        }

        return tokenHashtable;
    }
}
