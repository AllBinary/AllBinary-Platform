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
package org.allbinary.data.generator;

import java.io.IOException;

import org.allbinary.logic.io.AbDataInputStream;
import org.allbinary.logic.io.AbDataOutputStream;
import org.allbinary.logic.io.AbFileInputStream;
import org.allbinary.logic.io.AbFileOutputStream;
import org.allbinary.logic.io.DataOutputStreamFactory;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class UniqueIdGenerator implements IdGeneratorInterface
{
    public static final String EXT = ".unq";
            
   //private String uniqueIdFile;
   private AbFile newFile;
   private long id = 0;

   public UniqueIdGenerator()
   {
   }
   
   public void initialize(int value)
   {
      try
      {
         newFile.createNewFile();

         AbDataOutputStream idData =
             DataOutputStreamFactory.getInstance().getInstance(newFile);

         idData.writeLong(value);

      }catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().IDLOGGING))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "initialize", e));
         }
         
      }
   }
   
   public synchronized void setFile(String filePathName, String name) throws Exception
   {
       //uniqueIdFile=filePathName;
       newFile = new AbFile(filePathName);
   }
   
   public synchronized String getNext() throws IOException
   {
	   AbDataInputStream idData = null;
	   AbDataOutputStream idOutData = null;
      try
      {
    	 AbFileInputStream idFile = new AbFileInputStream(this.newFile);
    	 idData = new AbDataInputStream(idFile);
    	 
         id = idData.readLong();
         
         AbFileOutputStream idOutFile = new AbFileOutputStream(this.newFile);
         idOutData = new AbDataOutputStream(idOutFile);
         idOutData.writeLong(id + 1);
         
         Long idLong = new Long(id);
         return idLong.toString();
      }catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().IDLOGGING))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "getNext", e));
         }
         return "Error";
      }
      finally
      {
    	  StreamUtil.getInstance().close(idData);
    	  StreamUtil.getInstance().close(idOutData);
      }      
   }
}
