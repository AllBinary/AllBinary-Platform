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
package allbinary.logic.communication.smtp;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tree.dom.BasicErrorNodeUtil;
import allbinary.data.tree.dom.DomNodeInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.Vector;

public class EmailsNotUsed implements DomNodeInterface
{
   protected Vector emailInfoVector;

   public EmailsNotUsed() throws Exception
   {
      this.emailInfoVector = new Vector();
   }

   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PAYMENT))
         {
            LogUtil.put(LogFactory.getInstance("Start", this, "toXmlNode"));
         }

         Node node = document.createElement(EmailData.NAME);

         Iterator iter = this.emailInfoVector.iterator();
         while(iter.hasNext())
         {
            Email email = (Email) iter.next();

            Node emailNode = email.toXmlNode(document);

            if(emailNode!=null)
            {
               node.appendChild(emailNode);
            }
         }

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PAYMENT))
         {
            LogUtil.put(LogFactory.getInstance("End", this, "toXmlNode()"));
         }
         return node;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "toXmlNode()", e));
         }

         String error = "Error Processing Order.  Please contact us if error persists.<p/>";
         return BasicErrorNodeUtil.get(document, error);
      }
   }
}
