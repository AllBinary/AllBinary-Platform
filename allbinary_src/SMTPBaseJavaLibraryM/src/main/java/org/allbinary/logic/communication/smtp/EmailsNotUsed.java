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
package org.allbinary.logic.communication.smtp;


import java.util.Vector;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.BasicErrorNodeUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.string.CommonStrings;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class EmailsNotUsed implements DomNodeInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   protected Vector emailInfoVector;

   public EmailsNotUsed() throws Exception
   {
      this.emailInfoVector = new Vector();
   }

   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENT))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "toXmlNode"));
         }

         Node node = document.createElement(EmailData.NAME);

         final int size = emailInfoVector.size();
         for(int index = 0; index < size; index++)
         {
            Email email = (Email) emailInfoVector.get(index);

            Node emailNode = email.toXmlNode(document);

            if(emailNode!=null)
            {
               node.appendChild(emailNode);
            }
         }

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENT))
         {
            LogUtil.put(LogFactory.getInstance("End", this, "toXmlNode()"));
         }
         return node;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "toXmlNode()", e));
         }

         String error = "Error Processing Order.  Please contact us if error persists.<p/>";
         return BasicErrorNodeUtil.get(document, error);
      }
   }
}
