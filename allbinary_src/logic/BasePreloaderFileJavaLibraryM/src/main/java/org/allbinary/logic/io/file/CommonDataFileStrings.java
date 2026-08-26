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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class CommonDataFileStrings {
    
    private static final CommonDataFileStrings instance = new CommonDataFileStrings();

    /**
     * @return the instance
     */
    @JsMethod
    public static CommonDataFileStrings getInstance() {
        return instance;
    }
    
    //final CommonDataFileStrings commonDataFileStrings = CommonDataFileStrings.getInstance();
    @JsProperty
    public final String XML = "xml";
    @JsProperty
    public final String _XML = ".xml";
    @JsProperty
    public final String JSON = "json";
    @JsProperty
    public final String _JSON = ".json";

    @JsProperty
    public final String XSL = "xsl";
    @JsProperty
    public final String _XSL = ".xsl";
    
    @JsProperty
    public final String JAVA = "java";
    @JsProperty
    public final String _JAVA = ".java";
    @JsProperty
    public final String KT = "kt";
    @JsProperty
    public final String _KT = ".kt";
    @JsProperty
    public final String TS = "ts";
    @JsProperty
    public final String _TS = ".ts";
    @JsProperty
    public final String JS = "js";
    @JsProperty
    public final String _JS = ".js";
    @JsProperty
    public final String CPP = "cpp";
    @JsProperty
    public final String _CPP = ".cpp";

    @JsProperty
    public final String UNCRYPTED_EXTENSION = this.XML;
    @JsProperty
    public final String ENCRYPTED_EXTENSION = "abd";

}
