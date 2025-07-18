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
package tags;

import java.util.HashMap;

import org.allbinary.logic.communication.log.LogUtil;

public class PropertiesTag extends CommandTag
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private HashMap propertiesHashMap;
   
   public PropertiesTag()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
         logUtil.put(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
      }
      this.propertiesHashMap = new HashMap();
   }
   
   protected HashMap getPropertiesHashMap()
   {
      return this.propertiesHashMap;
   }     

   public int doEndTag()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
         logUtil.put("Tag Ended", this, "doEndTag");
      }
      this.propertiesHashMap = new HashMap();
      return EVAL_PAGE;
   }
}
