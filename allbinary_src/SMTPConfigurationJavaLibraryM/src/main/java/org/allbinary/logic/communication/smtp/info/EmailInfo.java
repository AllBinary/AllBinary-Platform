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
package org.allbinary.logic.communication.smtp.info;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class EmailInfo
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private BasicEmailInfo basicEmailInfo;
   private String htmlAttachment;
   private String contentBase;
   
   public EmailInfo(
      BasicEmailInfo basicEmailInfo)
      throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
      }
      
      this.init(basicEmailInfo, null, null);
   }
   
   public EmailInfo(
      BasicEmailInfo basicEmailInfo,
      String htmlAttachment,
      String contentBase)
      throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING))
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
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
