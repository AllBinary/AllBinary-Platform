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
package org.allbinary.logic.basic.io;

import org.allbinary.logic.basic.io.AbFileInputStream;
import org.allbinary.logic.communication.log.LogFactory;
import java.io.DataInputStream;
import org.allbinary.logic.basic.string.CommonStrings;

import org.allbinary.logic.communication.log.LogUtil;

public class LineReader
{
    private String fileName;
    private String string;
    private int index=0;
    private AbFileInputStream idFile;
    private DataInputStream idData;
    
    public LineReader(String fileName)
    {
        try
        {
            byte bytes[]= new byte[100000];
            this.fileName= fileName;
            idFile = new AbFileInputStream(fileName);
            idFile.read(bytes);
            string = new String(bytes);
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("File: " + fileName, this, "LineReader", e));
            //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.IDLOGGING))
            {
                
            }
        }
    }
    
    public boolean hasNext()
    {
        try
        {
            int nextIndex = string.indexOf("\n",index);
            if(nextIndex==-1) return false;
            return true;
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "hasNext", e));
            //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.IDLOGGING))
            {
                
            }
            return false;
        }
    }
    
    public String next() throws Exception
    {
        try
        {
            int nextIndex = string.indexOf("\n",index);

            if(nextIndex==-1)
            {
                throw new Exception("next() should have been called first");
            }

            String temp = string.substring(index, nextIndex);
            index=nextIndex+1;
            return temp;
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "getNext", e));
            //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.IDLOGGING))
            {
                
            }
            throw e;
        }
    }
}
