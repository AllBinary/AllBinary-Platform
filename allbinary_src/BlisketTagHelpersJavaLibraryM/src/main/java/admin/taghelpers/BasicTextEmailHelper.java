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
package admin.taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.communication.smtp.event.handler.factory.AdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.info.AdminEmailInfo;
import org.allbinary.logic.communication.smtp.info.BasicEmailInfo;
import org.allbinary.logic.communication.smtp.info.EmailInfo;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;

public class BasicTextEmailHelper
    extends TagHelper
{
    
    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();
    
   private HashMap hashMap;
   private PageContext pageContext;
   
   public BasicTextEmailHelper(HashMap hashMap, PageContext pageContext) throws Exception
   {
      this.pageContext = pageContext;
      this.hashMap = hashMap;
   }
   
   public void send() throws Exception
   {
      String adminEmailSubject = (String) hashMap.get("Subject");
      String adminEmailTextBody = (String) hashMap.get("Body");

      BasicEmailInfo adminBasicEmailInfo = (BasicEmailInfo)
      new AdminEmailInfo(adminEmailSubject, adminEmailTextBody);

      EmailInfo adminEmailInfo = new EmailInfo(adminBasicEmailInfo);

      //Send response to Admin(s)
      UserEmailEventHandler adminUserEmailEventHandler =
         AdminUserEmailEventHandlerSingletons.getInstance().getInstance(
             this.abeClientInformation, UserEmailEventNameData.INSTALLER);

      adminUserEmailEventHandler.receiveEmailInfo(
         UserEmailEventNameData.INSTALLER, adminEmailInfo);
   }
}
