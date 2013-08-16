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
package allbinary.globals;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tree.dom.DomNodeInterface;

public class UrlGlobalsViewFactory
{
   private UrlGlobalsViewFactory()
   {
   }

   public static DomNodeInterface getInstance()
   {
      try
      {
         return (DomNodeInterface) new UrlGlobalsView();
      }
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"GlobalsViewFactory","getInstance()",e));
         }
         return null;
      }
   }

}
