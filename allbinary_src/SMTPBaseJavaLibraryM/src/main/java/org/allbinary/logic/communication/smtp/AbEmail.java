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

import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class AbEmail
   implements EmailInterface
{
   private Email email;
   
   public AbEmail(
      String server, String from, String to, String subject, 
      String textBody, String htmlAttachment, String contentBase) 
      throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
      {
         this.email = new Email(
            from, to, subject, server, textBody, htmlAttachment, contentBase, true);
      }
      else
      {
         this.email = new Email(
            from, to, subject, server, textBody, htmlAttachment, contentBase);
      }
   }

   public MimeMessage getMimeMessage()
   {
      return this.email.getMimeMessage();
   }

   public String getDebugInfo()
   {
      return this.email.getDebugInfo();
   }

   public String log() throws Exception
   {
      return this.email.log();
   }

   public HashMap toHashMap() throws Exception
   {
      return this.email.toHashMap();
   }

   public Node toXmlNode(Document document) throws Exception
   {
      return this.email.toXmlNode(document);
   }

   public Email getEmail()
   {
      return (Email) this.email;
   }

   public Document toXmlDoc() throws Exception
   {
      return this.email.toXmlDoc();
   }
}
