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
package views.generic.user;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.business.user.UserInterface;

import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.UserDomNode;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class UserView extends HttpStoreComponentView
{
   protected UserInterface user;
   
   public UserView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) new UserDomNode(this.user));
   }
   
   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         String error = "Failed to view User";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
         }
         throw e;
      }
   }
}