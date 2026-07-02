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
package org.allbinary.logic.io.path;

//Used to correct user input for paths

import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;

public class AbPathData
{
    private static final AbPathData instance = new AbPathData();

    public static AbPathData getInstance()
    {
        return AbPathData.instance;
    }
    
   public final String EXTENSION_SEP = CommonSeps.getInstance().PERIOD;
   public final char SEPARATORCHAR = '/';
   public final String SEPARATOR = CommonSeps.getInstance().FORWARD_SLASH;
   
   private AbPathData()
   {
   }

    public int getExtensionIndex(String filePath)
    //throws Exception
    {
        int indexOfFileExtensionDelmiter = 
            filePath.lastIndexOf(this.EXTENSION_SEP);
        
        int indexOfLatDelimiter = filePath.lastIndexOf(this.SEPARATOR);
        
        if(indexOfFileExtensionDelmiter < 0)
        {
            //throw new Exception("No File Extension for: " + filePath);
            return -1;
        }
        
        if(indexOfFileExtensionDelmiter < indexOfLatDelimiter)
        {
            //throw new Exception("No File Extension for: " + filePath);
            return -1;
        }
        
      /*
      if(filePath.length() < indexOfFileExtensionDelmiter)
      {
         throw new Exception("Could not be a file path since its less than " + indexOfFileExtensionDelmiter + " characters");
      }
       */
        
        return indexOfFileExtensionDelmiter;
    }
    
    public String getExtension(String filePath) 
    //throws Exception
    {
        int indexOfFileExtensionDelmiter = this.getExtensionIndex(filePath);
        String extension = StringUtil.getInstance().EMPTY_STRING;
        
        if(indexOfFileExtensionDelmiter >= 0)
        {
            extension = filePath.substring(indexOfFileExtensionDelmiter + 1);
        }

        /*
        if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            PreLogUtil.put("FileExtension: " + extension, this, "getExtension()");
        }
        */
        
        return extension;
    }

    //Do not update without checking the removeNameFromPath in PathUtil
    public String removeNameFromPath(final String path, final char systemSep)
    {
        int endIndex = path.lastIndexOf(this.SEPARATOR);

        if(endIndex < 0)
        {
            endIndex = path.lastIndexOf(systemSep);
        }
        
        if(endIndex < 0)
        {
            return StringUtil.getInstance().EMPTY_STRING;
        }
        
        //if the last char is sep then use the last token as the name
        if(path.length() == endIndex + 1)
        {
            return this.removeNameFromPath(path.substring(0, endIndex - 1), systemSep);
        }
        else
        {
            String pathWithoutName = path.substring(0, endIndex);
            return pathWithoutName;
        }
    }
    
}
