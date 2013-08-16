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
package allbinary.logic.visual.transform.info;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.communication.http.request.session.WeblisketSession;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;

public class TransformInfoHttpComposite
{      
   private TransformInfoHttpInterface transformInfoInterface;

   public TransformInfoHttpComposite(TransformInfoInterface transformInfoInterface) //throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, "Constructor()"));
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