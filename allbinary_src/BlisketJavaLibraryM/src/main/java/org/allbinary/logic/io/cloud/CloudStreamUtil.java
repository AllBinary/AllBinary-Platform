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
package org.allbinary.logic.io;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import java.io.InputStream;

/**
 *
 * @author user
 */
public class CloudStreamUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                logUtil.put("Cloud File Bytes: " + available, this, "processRequest()");
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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                logUtil.put("Local File Bytes: " + available, this, "processRequest()");
            }
        } else
        {
            inputStream = new AbFileInputStream(file);

            available = inputStream.available();

            if (available > 0)
            {
                if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
                {
                    logUtil.put("Cloud File Bytes: " + available, this, "processRequest()");
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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                logUtil.put("Local File Bytes: " + available, this, "processRequest()");
            }
        }

        return inputStream;
    }

}
