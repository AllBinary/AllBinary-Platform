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
package org.allbinary.logic.java;

public class FileExtensionData
{

    private static final FileExtensionData instance = new FileExtensionData();

    /**
     * @return the instance
     */
    public static FileExtensionData getInstance()
    {
        return instance;
    }

    private FileExtensionData()
    {
    }

    public final String DOT_JAVA_SOURCE = ".java";
    public final String JAVA_SOURCE = "java";
}
