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

import org.allbinary.logic.io.file.directory.SubDirectory;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

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

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final SubDirectory subDirectory = SubDirectory.getInstance();
    
    private WeblisketFinder()
    {
    }

    //searches for all paths that contain the path provided
    public BasicArrayList findAll(String path)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "findAll"));

            return subDirectory.search(KEY, new AbFile(path));
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION this, "findAll", e));
            if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
            {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION this, "findAll", e));
            }

            //return new BasicArrayList();
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
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION this, "getInstallationPath", e));
            }
            return null;
        }
    }
    
    public static void main(String[] args) throws Exception {
        final BasicArrayList vector = WeblisketFinder.getInstance().findAll(
                ""
        );
        System.out.println(vector.size());
    }
}
