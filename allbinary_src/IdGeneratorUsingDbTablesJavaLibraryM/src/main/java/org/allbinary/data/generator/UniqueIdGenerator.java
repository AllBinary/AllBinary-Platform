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
import java.util.Vector;
import org.allbinary.data.tables.generator.IdGeneratorEntity;
import org.allbinary.data.tables.generator.IdGeneratorEntityFactory;

import org.allbinary.string.CommonStrings;

import org.allbinary.logic.communication.log.LogUtil;

public class UniqueIdGenerator implements IdGeneratorInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public static final String EXT = ".unq";
    
    private final IdGeneratorEntity idGeneratorEntity;
    
    private String name;
    
   public UniqueIdGenerator()
   {
       idGeneratorEntity = (IdGeneratorEntity) IdGeneratorEntityFactory.getInstance();
   }
   
   public void initialize(int value)
   {
      try
      {
          Vector vector = new Vector();
          vector.add(name);
          vector.add(Long.valueOf(value).toString());
          idGeneratorEntity.insert(vector);
      }catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().IDLOGGING))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "initialize", e);
         }
      }
   }
   
   public synchronized void setFile(String filePathName, String name) throws Exception
   {
       this.name = name;
   }
   
   public synchronized String getNext() throws IOException
   {
       try
       {
           Long idLong = this.idGeneratorEntity.get(name);
           
           Long newValue = Long.valueOf(idLong.longValue() + 1);
           
           this.idGeneratorEntity.update(name, newValue);

         return idLong.toString();
      }catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().IDLOGGING))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "getNext", e);
         }
         return "Error";
      }
   }
}
