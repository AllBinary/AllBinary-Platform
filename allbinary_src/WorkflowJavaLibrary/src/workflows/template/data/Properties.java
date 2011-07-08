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
package workflows.template.data;

import abcs.logic.communication.log.LogFactory;
import java.util.HashMap;

import javax.servlet.jsp.tagext.TagSupport;

import abcs.logic.communication.log.LogUtil;


public class Properties
{
   private HashMap propertiesHashMap;
   
   public Properties()
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance("Start/Tag",this,"Constructor"));
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
         LogUtil.put(LogFactory.getInstance("Tag Ended",this,"doEndTag"));
      }
      this.propertiesHashMap = new HashMap();
      return TagSupport.EVAL_PAGE;
   }
}
