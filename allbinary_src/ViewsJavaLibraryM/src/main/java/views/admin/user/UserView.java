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
package views.admin.user;

import org.allbinary.business.user.UserDomNode;
import org.allbinary.business.user.UserInterface;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import views.business.context.HttpContextView;

public class UserView extends HttpContextView
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   protected UserInterface user;
   
   public UserView(TransformInfoInterface transformInfoInterface) 
      throws Exception
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "view()", e);
         }
         throw e;
      }
   }
}