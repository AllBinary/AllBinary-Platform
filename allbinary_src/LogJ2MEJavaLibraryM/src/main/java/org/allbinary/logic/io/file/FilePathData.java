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
package org.allbinary.logic.io.file;

public class FilePathData {

    private static final FilePathData instance = new FilePathData();

    /**
     * @return the instance
     */
    public static FilePathData getInstance() {
        return FilePathData.instance;
    }

    //final
    public final char SEPARATORCHAR = '\\';

    public String PATH_START = "\\\\";

    private FilePathData() {
    }

}
