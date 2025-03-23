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

import admin.taghelpers.TagHelperFactoryInterface;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class HelperTag extends PropertiesTag
{    
   private final TagHelperFactoryInterface tagHelperFactoryInterface;

   private Object object;
   
   public HelperTag(final TagHelperFactoryInterface tagHelperFactoryInterface)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
    	  LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().CONSTRUCTOR, this, "HelperTag(TagHelperFactoryInterface)"));
      }

      this.tagHelperFactoryInterface = tagHelperFactoryInterface;
   }
   
   //This must occur in do start tag after properties are set
   protected void setHelper() throws Exception
   {
      if(this.object==null)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
         {
            LogUtil.put(LogFactory.getInstance("Creating TagHelper with: \n" +
            this.getPropertiesHashMap().toString() ,this,"doStartTag"));
         }
         
         
         this.object = tagHelperFactoryInterface.getInstance(
             this.getPropertiesHashMap(), this.pageContext);
      }
   }
   
   //Used after set helper for calling methods with reflection
   public Object getHelper()
   {
      return this.object;
   }
   
   public int doEndTag()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance("Tag Ended",this,"doEndTag"));
      }
      this.object = null;
      return super.doEndTag();
   }
}
