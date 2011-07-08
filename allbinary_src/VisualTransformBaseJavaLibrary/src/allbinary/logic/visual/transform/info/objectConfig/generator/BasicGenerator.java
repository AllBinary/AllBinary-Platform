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
package allbinary.logic.visual.transform.info.objectConfig.generator;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class BasicGenerator 
   implements TransformInfoObjectConfigGeneratorInterface
{
   public BasicGenerator()
   {
   }
   
   public String process(String input)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Processing", this, "process()"));
      }      
      return input;
   }
}
