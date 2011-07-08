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

import abcs.logic.communication.log.LogFactory;
import java.util.HashMap;

import abcs.logic.communication.log.LogUtil;

public class PropertiesTag extends CommandTag
{
   private HashMap propertiesHashMap;
   
   public PropertiesTag()
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance("Tag Constructed", this, "Constructor()"));
      }
      this.propertiesHashMap = new HashMap();
   }
   
   protected HashMap getPropertiesHashMap()
   {
      return this.propertiesHashMap;
   }     

   public int doEndTag()
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance("Tag Ended", this, "doEndTag"));
      }
      this.propertiesHashMap = new HashMap();
      return EVAL_PAGE;
   }
}
