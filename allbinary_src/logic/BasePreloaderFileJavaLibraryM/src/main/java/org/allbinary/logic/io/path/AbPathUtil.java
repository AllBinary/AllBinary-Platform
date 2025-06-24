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

import org.allbinary.logic.io.file.FilePathData;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

//Used to correct user input for paths
public class AbPathUtil
{
    private static final AbPathUtil instance = new AbPathUtil();

    public static AbPathUtil getInstance()
    {
        return instance;
    }

    private final AbPathData abPathData = AbPathData.getInstance();
    
    private AbPathUtil()
    {
    }
    
    private int getExtensionIndex(String filePath)
    //throws Exception
    {
        int indexOfFileExtensionDelmiter = 
            filePath.lastIndexOf(abPathData.EXTENSION_SEP);
        
        int indexOfLatDelimiter = filePath.lastIndexOf(abPathData.SEPARATORCHAR);
        
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
    
    public String getExtension(AbFilePath abFilePath)
    //throws Exception
    {
        return this.getExtension(abFilePath.toString());
    }
    
    public String getExtension(AbPath abPath)
    //throws Exception
    {
        return this.getExtension(abPath.toString());
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
    
    public String getWithoutExtension(AbFilePath abFilePath)
    throws Exception
    {
        return this.getWithoutExtension(abFilePath.toString());
    }
    
    public String getWithoutExtension(AbPath abPath)
    throws Exception
    {
        return this.getWithoutExtension(abPath.toString());
    }
    
    public String getWithoutExtension(String filePath) throws Exception
    {
        int indexOfFileExtensionDelmiter = this.getExtensionIndex(filePath);
        //No extension by default
        String pathWithoutExtension = filePath;
        
        if(indexOfFileExtensionDelmiter >= 0)
        {
            pathWithoutExtension = filePath.substring(0, indexOfFileExtensionDelmiter);
        }
        /*
        if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            PreLogUtil.put("FileWithoutExtension: " + pathWithoutExtension, this, "getWithoutExtension()");
        }
        */
        return pathWithoutExtension;
    }
    
    public boolean isValid(String path)
    {
        if(this.isValidStart(path))
        {
            if(this.isValidEnd(path))
            {
                return true;
            }
        }
        return false;
    }
    
    private boolean isValidStart(String path)
    {
        if(path.charAt(0) == abPathData.SEPARATORCHAR ||
           path.charAt(0) == '.')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private boolean isValidEnd(String path)
    {
        final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if(stringValidationUtil.isEmpty(path))
        {
            return false;
        }
        
        if(path.charAt(path.length()-1) == abPathData.SEPARATORCHAR)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String adjustStart(String path)
    {
        if(!this.isValidStart(path))
        {
            path = abPathData.SEPARATOR + path;
        }
        return path;
    }

    public String adjustEnd(String path)
    {
        if(!this.isValidEnd(path))
        {
            path = path + abPathData.SEPARATOR;
        }
        return path;
    }
    
    public String adjust(String path)
    {
        final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
        
        if(stringValidationUtil.isEmpty(path))
        {
            return abPathData.SEPARATOR;
        }

        //Convert \ to / I.E. from dos/windows file path to uri/unix path
        if(path.indexOf("\\") >= 0)
        {
            //PreLogUtil.put("Adjusting Path: " + path, this, "adjust");
            path = path.replace('\\', abPathData.SEPARATORCHAR);
            //PreLogUtil.put("Adjusted Path: " + path, this, "adjust");
        }
        /*
        if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FILE))
        {
            PreLogUtil.put("Path: " + path, this, "adjust");
        }
        */
        
        return path;
    }
    
    public synchronized String getNameFromPath(String categoryPath)
    {
        int endIndex = categoryPath.lastIndexOf(
                abPathData.SEPARATOR);
        
        if(endIndex < 0)
        {
            endIndex = categoryPath.lastIndexOf(FilePathData.SEPARATORCHAR);
        }
        
        if(endIndex < 0) return categoryPath;
        
        //if the last char is sep then use the last token as the name
        if(categoryPath.length() == endIndex + 1)
        {
            String categoryName = categoryPath.substring(0, endIndex);
            return this.getNameFromPath(categoryName);
        }
        else
        {
            String categoryName = categoryPath.substring(endIndex + 1);
            return categoryName;
        }
    }
    
    public synchronized AbPath removeNameFromPath(String path) throws Exception
    {
        int endIndex = path.lastIndexOf(abPathData.SEPARATOR);

        if(endIndex < 0)
        {
            endIndex = path.lastIndexOf(FilePathData.SEPARATORCHAR);
        }
        
        if(endIndex < 0)
        {
            return new AbPath();
        }
        
        //if the last char is sep then use the last token as the name
        if(path.length() == endIndex + 1)
        {
            return this.removeNameFromPath(path.substring(0, endIndex - 1));
        }
        else
        {
            String categoryName = path.substring(0, endIndex);
            return new AbPath(categoryName);
        }
    }

}
