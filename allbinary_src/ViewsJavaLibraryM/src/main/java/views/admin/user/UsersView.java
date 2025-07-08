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


import org.allbinary.logic.communication.log.LogFactory;

import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

   
import org.allbinary.logic.communication.log.LogUtil;


import org.allbinary.business.user.UsersData;
import org.allbinary.business.user.UserDomNode;
import org.allbinary.business.user.modules.User;

import org.allbinary.data.tree.dom.DomNodeInterface;



import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import views.business.context.HttpContextView;

public class UsersView extends HttpContextView implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   protected Vector userVector;
   
   public UsersView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
      
   public Node toXmlNode(Document document)
   {
      try
      {
         Node usersNode = document.createElement(UsersData.NAME);                  
         
         int size = userVector.size();
         for (int index = 0; index < size; index++)
         {
            User userInterface = (User) userVector.get(index);
            if(userInterface!=null)
            {
               Node node = new UserDomNode(userInterface).toXmlNode(document);
               usersNode.appendChild(node);
            }
            else
            {
               logUtil.put("UserHelper",this,"toXmlNode");
            }
         }
         
         return usersNode;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGINGERROR))
         {
            logUtil.put(this.commonStrings.FAILURE, this, "toXmlNode", e);
         }
         return null;
      }
   }

   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this);
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
            logUtil.put(commonStrings.EXCEPTION,this,"view()",e);
         }
         throw e;
      }
   }   
}
