/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
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
package org.allbinary.logic.io.file;


/**
 *
 * @author User
 */

public class CommonDataFileStrings {
    
    private static final CommonDataFileStrings instance = new CommonDataFileStrings();

    /**
     * @return the instance
     */
    public static CommonDataFileStrings getInstance() {
        return CommonDataFileStrings.instance;
    }
    
    //final CommonDataFileStrings commonDataFileStrings = CommonDataFileStrings.getInstance();
    public final String XML = "xml";
    public final String _XML = ".xml";
    public final String JSON = "json";
    public final String _JSON = ".json";

    public final String XSL = "xsl";
    public final String _XSL = ".xsl";
    
    public final String JAVA = "java";
    public final String _JAVA = ".java";
    public final String KT = "kt";
    public final String _KT = ".kt";
    public final String TS = "ts";
    public final String _TS = ".ts";
    public final String JS = "js";
    public final String _JS = ".js";
    public final String CPP = "cpp";
    public final String _CPP = ".cpp";
    public final String H = "h";
    public final String _H = ".h";
    public final String PY = "py";
    public final String _PY = ".py";
    public final String GO = "go";
    public final String _GO = ".go";
    public final String CS = "cs";
    public final String _CS = ".cs";
    public final String ADB = "adb";
    public final String _ADB = ".adb";
    public final String ADS = "ads";
    public final String _ADS = ".ads";
    public final String ADA = "ada";
    public final String _ADA = ".ada";
    public final String RUST = "rs";
    public final String _RUST = ".rs";
    public final String C = "c";
    public final String _C = ".c";
    public final String CSHARP = "cs";
    public final String _CSHARP = ".cs";
    public final String ERL = "erl";
    public final String _ERL = ".erl";
    public final String HS = "hs";
    public final String _HS = ".hs";
    public final String HX = "hx";
    public final String _HX = ".hx";
    public final String LUA = "lua";
    public final String _LUA = ".lua";
    public final String PAS = "pas";
    public final String _PAS = ".pas";
    public final String ML = "ml";
    public final String _ML = ".ml";
    public final String PL = "pl";
    public final String _PL = ".pl";
    public final String RB = "rb";
    public final String _RB = ".rb";
    public final String ZIG = "zig";
    public final String _ZIG = ".zig";

    public final String UNCRYPTED_EXTENSION = this.XML;
    public final String ENCRYPTED_EXTENSION = "abd";

}
