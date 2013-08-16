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
package abcs.logic.basic.io.file;

public class FileData
{
    private static final FileData instance = new FileData();

    public static FileData getInstance()
    {
        return instance;
    }

    private FileData()
    {
    }

    public final String NAME = "FILE_NAME";
    public final String ISFILE = "FILE_ISFILE";
    public final String ISDIRECTORY = "FILE_ISDIRECTORY";
    public final String ISHIDDEN = "FILE_ISHIDDEN";
    public final String ISABSOLUTE = "FILE_ISABSOLUTE";
    public final String ROOT_NAME = "FILE_ROOT_NAME";
    public final String PATH = "FILE_PATH";
    public final String ABSOLUTE_PATH = "FILE_ABSOLUTE_PATH";
    public final String CANONICAL_PATH = "FILE_CANONICAL_PATH";
    public final String PARENT = "FILE_PARENT";

    public final int MINLEN = 1;
    public final int MAXLEN = 120;
    public final int MAXIMAGEFILESIZE = 1048576;
    public final int MINIMAGEFILESIZE = 32;

    public final int MAXDOWNLOADABLEFILESIZE = 1048576 * 20;
    public final int MINDOWNLOADABLEFILESIZE = 1;
}
