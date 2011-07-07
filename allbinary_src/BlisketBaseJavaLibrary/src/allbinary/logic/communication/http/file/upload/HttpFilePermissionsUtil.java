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
package allbinary.logic.communication.http.file.upload;


import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.path.AbPath;

public class HttpFilePermissionsUtil {

    private static final HttpFilePermissionsUtil instance = new HttpFilePermissionsUtil();

    /**
     * @return the instance
     */
    public static HttpFilePermissionsUtil getInstance()
    {
        return instance;
    }
    
    public void create(AbPath path)
        throws Exception
    {
        AbFile file = new AbFile(path);

        if(!file.isFile())
        {
            file.createNewFile();
        }
    }
}
