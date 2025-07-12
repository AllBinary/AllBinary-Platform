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

import org.allbinary.logic.communication.smtp.info.BasicEmailInfo;
import org.allbinary.logic.communication.smtp.info.EmailInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class BasicEmail 
   implements EmailInterface
{
   private AbEmail email;
   
   public BasicEmail(EmailInfo emailInfo, String to) throws Exception
   {
      BasicEmailInfo basicEmailInfo = emailInfo.getBasicEmailInfo();
               
      this.email = new AbEmail(
         basicEmailInfo.getEmailServerConfigurationInterface().getSmtpServer(),
         basicEmailInfo.getEmailServerConfigurationInterface().getAccountName(), 
         to, basicEmailInfo.getSubject(),
         basicEmailInfo.getTextBody(),
         emailInfo.getHtmlAttachment(),
         emailInfo.getContentBase());
   }
   
   public Email getEmail()
   {
      return (Email) this.email.getEmail();
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
   
   public Document toXmlDoc() throws Exception
   {
      return this.email.toXmlDoc();
   }

}
