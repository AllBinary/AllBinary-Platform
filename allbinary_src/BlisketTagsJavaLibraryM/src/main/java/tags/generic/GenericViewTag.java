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
package tags.generic;


import org.allbinary.logic.io.InputOutputTypeData;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;




import tags.TransformTag;

public class GenericViewTag extends TransformTag
{
   private String output;

   //defines the storage name/location for results and/or data submission type
   private String file;

   public GenericViewTag()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance(this.commonStrings.START,this,this.commonStrings.CONSTRUCTOR));
      }
   }

   public void setOutput(String value)
   {
      this.output = value;
      this.getPropertiesHashMap().put(InputOutputTypeData.getInstance().NAME, this.output);
   }

   public void setFile(String value)
   {
      this.file = value;
      this.getPropertiesHashMap().put(InputOutputTypeData.getInstance().FILE, this.file);
   }
}
