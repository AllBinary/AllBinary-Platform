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

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.InternetHeaders;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
//TWB - GAE does not have InetAddress class
//import java.net.InetAddress;
import java.util.HashMap;
import java.util.Properties;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.ModDomHelper;
   
import org.allbinary.string.CommonStrings;

public class Email 
   implements EmailInterface
{
   private MimeMessage msg;
   private Properties properties;
   
   private ByteArrayOutputStream bs;
   private boolean isDebug;
   
   private static final String DEBUG = "mail.debug";
   private static final String SMTP_HOST = "mail.smtp.host";
   private static final String SMTP_PORT = "mail.smtp.port";
   private static final String SMTP_USER = "mail.smtp.user";
   private static final String SMTP_RETURN_ADDRESS = "mail.from";
   //Used for HELO
   private static final String SMTP_LOCAL_HOST = "mail.smtp.localhost";
   
   public Email(String from, String to, String subject, String server,
      String textBody, String  htmlAttachment, String  contentBase) throws Exception
   {
      this.isDebug = false;
      init(from, to, subject, server, textBody,
         htmlAttachment, contentBase);
   }
   
   public Email(String from, String to, String subject, String server,
      String textBody, String  htmlAttachment, String  contentBase, boolean isDebug) throws Exception
   {
      this.isDebug = isDebug;
      init(from, to, subject, server, textBody,
         htmlAttachment, contentBase);
   }
   
   public void init(String from, String to, String subject, String server,
      String textBody, String  htmlAttachment, String  contentBase)
      throws Exception
   {
      try
      {
          StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
          
         MimeBodyPart [ ]  mimeBodyParts = new MimeBodyPart [ !stringValidationUtil.isEmpty(htmlAttachment) ? 2 : 1 ];
         mimeBodyParts [ 0 ] = new MimeBodyPart( );
         mimeBodyParts [ 0 ].setText( textBody );
         
         if(!stringValidationUtil.isEmpty(htmlAttachment))
         {
            InternetHeaders  internetHeaders = new InternetHeaders( );
            internetHeaders.addHeader( "Content-Type", "text/html" );
            if(!stringValidationUtil.isEmpty(contentBase))
            {
               internetHeaders.addHeader( "Content-Base", contentBase );
            }
            mimeBodyParts [ 1 ] = new MimeBodyPart( internetHeaders, htmlAttachment.getBytes( ) );
         }
         init(server, ( Authenticator ) null,
            new InternetAddress [ ]
         { new InternetAddress( from ) },
            new InternetAddress [ ]
         { new InternetAddress( to   ) },
            ( InternetAddress [ ] ) null,
            ( InternetAddress [ ] ) null,
            subject,
            mimeBodyParts);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "emailConstructor", e));
         }
         throw e;
      }
   }
   
   public void init(
      String               server,
      Authenticator        authenticator,
      InternetAddress [ ]  addresses_from,
      InternetAddress [ ]  addresses_to,
      InternetAddress [ ]  addresses_cc,
      InternetAddress [ ]  addresses_bcc,
      String               subject,
      MimeBodyPart [ ]     mimeBodyParts)
      throws MessagingException
   {
      this.properties = new Properties();
      this.properties.put(SMTP_HOST,server);
      
      try
      {
         String hostName = 
             StringUtil.getInstance().EMPTY_STRING;
             //TWB - GAE does not have InetAddress class
             //InetAddress.getLocalHost().getHostName();

         if(StringValidationUtil.getInstance().isEmpty(hostName))
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
            {
               LogUtil.put(LogFactory.getInstance("Unable to get HostName so using fake", this, "init()"));
            }
            this.properties.put(SMTP_LOCAL_HOST, "FakeHostName");
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Continuing on Exception: Unable to get HostName", this, "init()", e));
         }
         
         this.properties.put(SMTP_LOCAL_HOST, "FakeHostName");
      }
      
      if(isDebug)
      {
         this.properties.put(DEBUG, "true");
      }
      
      Session session = Session.getInstance(properties, authenticator);
      
      if(isDebug)
      {
         session.setDebug(true);
         bs = new ByteArrayOutputStream();
         PrintStream printStream = new PrintStream(bs);
         session.setDebugOut(printStream);
      }
      
      msg = new MimeMessage(session);
      
      msg.addFrom( addresses_from );
      msg.setRecipients( Message.RecipientType.TO , addresses_to  );
      msg.setRecipients( Message.RecipientType.CC , addresses_cc  );
      msg.setRecipients( Message.RecipientType.BCC, addresses_bcc );
      msg.setSubject( subject );
      
      MimeMultipart  mimeMultipart = new MimeMultipart( );
      for ( int  i = 0; i < mimeBodyParts.length; i++ )
      {
         mimeMultipart.addBodyPart( mimeBodyParts [ i ] );
      }
      msg.setContent( mimeMultipart );
   }

   public MimeMessage getMimeMessage()
   {
      return this.msg;
   }
   
   public String getDebugInfo()
   {
      if(isDebug)
      {
         //"Size: " + bs.size()
         return "More Logging Before This To Standard Out: " + new String(bs.toString());
      }
      else
      {
         return "Debugging Is Off";
      }
   }
   
   public String log() throws Exception
   {
      return "\nHashMap: " + this.toHashMap().toString();
   }

   public HashMap toHashMap() throws Exception
   {
      try
      {
         HashMap hashMap = new HashMap();
         
         hashMap.put(EmailData.SERVER, (String) properties.get(SMTP_HOST));
         
         Address[] addresses = msg.getFrom();
         if(addresses != null)
         {
            for(int index = 0; index < addresses.length; index++)
            {
               hashMap.put(EmailData.FROM, addresses[index].toString());
            }
         }
         
         addresses = msg.getRecipients(Message.RecipientType.TO);
         if(addresses != null)
         {
            for(int index = 0; index < addresses.length; index++)
            {
               hashMap.put(EmailData.TO, addresses[index].toString());
            }
         }
         
         addresses = msg.getRecipients(Message.RecipientType.CC);
         if(addresses != null)
         {
            for(int index = 0; index < addresses.length; index++)
            {
               hashMap.put(EmailData.CC, addresses[index].toString());
            }
         }
         
         addresses = msg.getRecipients(Message.RecipientType.BCC);
         if(addresses != null)
         {
            for(int index = 0; index < addresses.length; index++)
            {
               hashMap.put(EmailData.BCC, addresses[index].toString());
            }
         }
         
         hashMap.put(EmailData.SUBJECT, msg.getSubject());
         
         MimeMultipart mimeMultipart = (MimeMultipart) msg.getContent();
         
         if(mimeMultipart != null)
         {
            for(int index = 0; index < mimeMultipart.getCount(); index++)
            {
               String content = (String) mimeMultipart.getBodyPart(index).getContent();
               hashMap.put(EmailData.CONTENT, content);
            }
         }
         
         return hashMap;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION this, "toHashMap()", e));
         }
         throw e;
      }
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      final Node node = ModDomHelper.createNameValueNodes(document, EmailData.NAME, this.toHashMap());
      return node;
   }

   public Document toXmlDoc() throws Exception
   {
      throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
   }

}
