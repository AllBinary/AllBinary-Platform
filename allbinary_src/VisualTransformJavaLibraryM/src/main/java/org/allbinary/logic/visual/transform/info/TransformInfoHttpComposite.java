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
package org.allbinary.logic.visual.transform.info;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;
import org.allbinary.string.CommonStrings;

public class TransformInfoHttpComposite
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private TransformInfoHttpInterface transformInfoInterface;

   public TransformInfoHttpComposite(TransformInfoInterface transformInfoInterface) //throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, this.commonStrings.CONSTRUCTOR));
      }

      this.transformInfoInterface = (TransformInfoHttpInterface) transformInfoInterface;
   }

   public TransformInfoInterface getTransformInfoInterface() throws Exception
   {
      return this.transformInfoInterface;
   }

   public WeblisketSession getWeblisketSession()
   {
      return new WeblisketSession(this.getPropertiesHashMap(), this.getPageContext());
   }

   public PageContext getPageContext()
   {
      return this.transformInfoInterface.getPageContext();
   }
   
   public HashMap getPropertiesHashMap()
   {
      return this.transformInfoInterface.getPropertiesHashMap();
   }
}