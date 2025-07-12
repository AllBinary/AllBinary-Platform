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

import java.util.HashMap;

import javax.servlet.jsp.tagext.TagSupport;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;


public class Properties
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private HashMap propertiesHashMap;
   
   public Properties()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
         logUtil.put("Start/Tag",this,this.commonStrings.CONSTRUCTOR);
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
         logUtil.put("Tag Ended",this,"doEndTag");
      }
      this.propertiesHashMap = new HashMap();
      return TagSupport.EVAL_PAGE;
   }
}
