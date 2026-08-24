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
package org.allbinary.string;

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringMaker;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class CommonSeps
{

    private static final CommonSeps instance = new CommonSeps();

    @JsMethod
    public static CommonSeps getInstance()
    {
        return CommonSeps.instance;
    }

    @JsProperty
    public final String NEW_LINE = "\n";

    @JsProperty
    public final String SEMICOLON = ";";
    @JsProperty
    public final String EQUALS = "=";
    @JsProperty
    public final String SPACE = " ";
    @JsProperty
    public final String COMMA = ",";
    @JsProperty
    public final String COLON = ":";
    @JsProperty
    public final String PERIOD = ".";
    @JsProperty
    public final String UNDERSCORE = "_";
    @JsProperty
    public final String COLON_SEP = new StringMaker().append(this.COLON).append(this.SPACE).toString();
    @JsProperty
    public final String COMMA_SEP = new StringMaker().append(this.COMMA).append(this.SPACE).toString();
    @JsProperty
    public final String PARENTHESIS_OPEN = "(";
    @JsProperty
    public final String PARENTHESIS_CLOSE = ")";
    @JsProperty
    public final String BRACE_OPEN = "{";
    @JsProperty
    public final String BRACE_CLOSE = "}";
    @JsProperty
    public final String BRACKET_OPEN = "[";
    @JsProperty
    public final String BRACKET_CLOSE = "]";
    
    @JsProperty
    public final String DASH = "-";

    @JsProperty
    public final String PERCENT = "%";
    @JsProperty
    public final String AMPERSAND = "&";
    
    @JsProperty
    public final String FORWARD_SLASH = "/";
    @JsProperty
    public final String BACK_SLASH = "\\";

    @JsProperty
    public final String QUESTION = "?";
    
    @JsProperty
    public final String QUOTE = "\"";
    @JsProperty
    public final String QUOTE_END = "\",";
    
    @JsProperty
    public final String COMMENT = "//";
    
    @JsProperty
    public final String INDENT = "    ";
}
