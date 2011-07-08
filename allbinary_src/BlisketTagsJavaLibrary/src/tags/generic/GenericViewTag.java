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


import abcs.logic.basic.io.InputOutputTypeData;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;




import tags.TransformTag;

public class GenericViewTag extends TransformTag
{
   private String output;

   //defines the storage name/location for results and/or data submission type
   private String file;

   public GenericViewTag()
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPTAG))
      {
         LogUtil.put(LogFactory.getInstance("Tag Constructed",this,"GenericViewTag()"));
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
