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
        return instance;
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

    public final String UNCRYPTED_EXTENSION = this.XML;
    public final String ENCRYPTED_EXTENSION = "abd";

}
