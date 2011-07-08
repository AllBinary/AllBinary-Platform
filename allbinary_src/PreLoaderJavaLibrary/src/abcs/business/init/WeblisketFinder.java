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
package abcs.business.init;

import java.util.Vector;

import abcs.logic.basic.io.file.directory.SubDirectory;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.communication.log.LogFactory;

import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.config.type.LogConfigType;
import abcs.logic.communication.log.config.type.LogConfigTypes;

public class WeblisketFinder
{
	private static final WeblisketFinder instance = new WeblisketFinder();
	
    private static final String KEY = "installerdata.dat";

    private WeblisketFinder()
    {
    }

    //searches for all paths that contain the path provided
    public static Vector findAll(String path)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Start", instance, "findAll"));

            SubDirectory subDirectory = new SubDirectory();
            return subDirectory.search(KEY, new AbFile(path));
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Failed", instance, "findAll", e));
            if (LogConfigTypes.LOGGING.contains(LogConfigType.PRELOADERERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed", instance, "findAll", e));
            }

            //return new Vector();
            return null;
        }
    }

    //Retrieves the intallation path
    public static String getInstallationPath(AbFile file)
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
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADERERROR))
            {
                LogUtil.put(LogFactory.getInstance("Failed", instance, "getInstallationPath", e));
            }
            return null;
        }
    }
}
