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


import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.data.tree.dom.BasicErrorNodeUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.StdUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

public class EmailsNotUsed implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   protected BasicArrayList emailInfoVector;

   public EmailsNotUsed() throws Exception
   {
      this.emailInfoVector = new BasicArrayListD();
   }

   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PAYMENT))
         {
            this.logUtil.putF(this.commonStrings.START, this, "toXmlNode");
         }

         Node node = document.createElement(EmailData.NAME);

         final int size = this.emailInfoVector.size();
         for(int index = 0; index < size; index++)
         {
            Email email = (Email) this.emailInfoVector.get(index);

            Node emailNode = email.toXmlNode(document);

            if(emailNode!=null)
            {
               node.appendChild(emailNode);
            }
         }

         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PAYMENT))
         {
            this.logUtil.putF(this.commonStrings.END, this, "toXmlNode()");
         }
         return node;
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "toXmlNode()", e);
         }

         String error = "Error Processing Order.  Please contact us if error persists.<p/>";
         return BasicErrorNodeUtil.get(document, error);
      }
   }
}
