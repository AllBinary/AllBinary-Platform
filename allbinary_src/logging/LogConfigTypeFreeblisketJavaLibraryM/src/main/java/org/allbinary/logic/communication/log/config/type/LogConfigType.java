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
package org.allbinary.logic.communication.log.config.type;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class LogConfigType
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String name;
   private String description;
   
   public static BasicArrayList availableLogConfigTypes = new BasicArrayList();
      
   public LogConfigType(String name, String description)
   {
       //logUtil.put(name, this, commonStrings.CONSTRUCTOR);
       
      this.name = name;
      this.description = description;
      availableLogConfigTypes.add(this);
   }

   public String getName()
   {
      return this.name;
   }

   public String getDescription()
   {
      return this.description;
   }

   public void setName(String value)
   {
      this.name = value;
   }

   public void setDescription(String value)
   {
      this.description = value;
   }
   
}
