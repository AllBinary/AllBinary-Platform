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
package org.allbinary.business.init;

import java.util.Vector;

import org.allbinary.logic.basic.io.file.directory.SubDirectory;
import org.allbinary.logic.basic.io.file.AbFile;
import org.allbinary.logic.basic.io.file.directory.Directory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

public class WeblisketFinder
{
    private static final WeblisketFinder instance = new WeblisketFinder();

    /**
     * @return the instance
     */
    public static WeblisketFinder getInstance() {
        return instance;
    }
	
    private static final String KEY = "installerdata.dat";

    private WeblisketFinder()
    {
    }

    //searches for all paths that contain the path provided
    public Vector findAll(String path)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Start", this, "findAll"));

            SubDirectory subDirectory = new SubDirectory();
            return subDirectory.search(KEY, new AbFile(path));
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Failed", this, "findAll", e));
            if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed", this, "findAll", e));
            }

            //return new Vector();
            return null;
        }
    }

    //Retrieves the intallation path
    public String getInstallationPath(AbFile file)
    {
        try
        {
            String filePath = file.getPath();
            int end = filePath.indexOf(KEY);
            if (end < 0)
            {
                return null;
            }
            return filePath.substring(0, end);
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRELOADERERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed", this, "getInstallationPath", e));
            }
            return null;
        }
    }
    
    public static void main(String[] args) throws Exception {
        final Vector vector = WeblisketFinder.getInstance().findAll(
                ""
        );
        System.out.println(vector.size());
    }
}
