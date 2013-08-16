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
package abcs.logic.basic.io;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import java.io.InputStream;

/**
 *
 * @author user
 */
public class CloudStreamUtil {

    private static final CloudStreamUtil instance = new CloudStreamUtil();

    /**
     * @return the instance
     */
    public static CloudStreamUtil getInstance()
    {
        return instance;
    }

    public InputStream getFile(AbFile file)
        throws Exception
    {
        InputStream inputStream = new AbFileInputStream(file);

        int available = inputStream.available();

        if (available > 0)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Cloud File Bytes: " + available, this, "processRequest()"));
            }
        }

        return inputStream;
    }

    public InputStream getFileAnyWhere(AbFile file)
        throws Exception
    {
        InputStream inputStream = new AbFileLocalInputStream(file);

        int available = inputStream.available();

        if (available > 0)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Local File Bytes: " + available, this, "processRequest()"));
            }
        } else
        {
            inputStream = new AbFileInputStream(file);

            available = inputStream.available();

            if (available > 0)
            {
                if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
                {
                    LogUtil.put(LogFactory.getInstance("Cloud File Bytes: " + available, this, "processRequest()"));
                }
            }
        }

        return inputStream;
    }

    public InputStream getFileLocal(AbFile file)
        throws Exception
    {
        InputStream inputStream = new AbFileLocalInputStream(file);

        int available = inputStream.available();

        if (available > 0)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Local File Bytes: " + available, this, "processRequest()"));
            }
        }

        return inputStream;
    }

}
