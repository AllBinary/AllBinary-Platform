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
package allbinary.logic.communication.smtp.info;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class EmailInfo
{
   private BasicEmailInfo basicEmailInfo;
   private String htmlAttachment;
   private String contentBase;
   
   public EmailInfo(
      BasicEmailInfo basicEmailInfo)
      throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "EmailInfo"));
      }
      
      this.init(basicEmailInfo, null, null);
   }
   
   public EmailInfo(
      BasicEmailInfo basicEmailInfo,
      String htmlAttachment,
      String contentBase)
      throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "EmailInfo"));
      }
      
      this.init(basicEmailInfo, null, null);
   }

   private void init(      
      BasicEmailInfo basicEmailInfo,
      String htmlAttachment,
      String contentBase)
   {
      this.basicEmailInfo = basicEmailInfo;
      this.htmlAttachment = htmlAttachment;
      this.contentBase = contentBase;
   }

   public BasicEmailInfo getBasicEmailInfo()
   {
      return this.basicEmailInfo;
   }
   
   public String getHtmlAttachment()
   {
      return this.htmlAttachment;
   }
   
   public String getContentBase()
   {
      return this.contentBase;
   }
   
   public String toString()
   {
      return "Email Info: \n " + this.getBasicEmailInfo().toString();
   }   
}
