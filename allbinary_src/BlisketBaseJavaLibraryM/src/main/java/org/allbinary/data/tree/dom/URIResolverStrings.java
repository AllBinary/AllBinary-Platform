/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.data.tree.dom;

/**
 *
 * @author User
 */
public class URIResolverStrings {
    
    private static final URIResolverStrings instance = new URIResolverStrings();
    
    /**
     * @return the instance
     */
    public static URIResolverStrings getInstance() {
        return instance;
    }

    public final String IMPORT_URL = "/{import url}";
    public final String ATTEMPT = "attempt to use xsl:import: href=";
    public final String BASE = "\nBase= ";
    public final String NEW_PATH = "\nNew path= ";
    public final String NOTE = "\nNote: ";
    public final String URL_GLOBAL = " is a urlglobal";
    public final String REQUIRED_EXTENSION = "\nRequired Extension: ";
    public final String RESOLVE = "resolve";
    
}
