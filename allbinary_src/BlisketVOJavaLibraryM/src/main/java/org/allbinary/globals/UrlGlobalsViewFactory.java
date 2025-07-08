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
package org.allbinary.globals;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.string.CommonStrings;

public class UrlGlobalsViewFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private UrlGlobalsViewFactory()
   {
   }

   public static DomNodeInterface getInstance()
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         return (DomNodeInterface) new UrlGlobalsView();
      }
      catch(Exception e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, "GlobalsViewFactory",commonStrings.GET_INSTANCE,e);
         }
         return null;
      }
   }

}
