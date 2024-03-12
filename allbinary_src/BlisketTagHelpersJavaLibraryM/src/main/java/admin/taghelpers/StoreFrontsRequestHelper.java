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
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFront;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.data.tables.context.module.storefronts.StoreFrontsEntityFactory;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.communication.smtp.event.handler.factory.AdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.event.handler.factory.StoreAdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.info.AdminEmailInfo;
import org.allbinary.logic.communication.smtp.info.BasicEmailInfo;
import org.allbinary.logic.communication.smtp.info.EmailInfo;
import org.allbinary.logic.communication.smtp.info.StoreEmailInfo;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;

public class StoreFrontsRequestHelper implements ModifyTableInterface
{
    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();
    
    private final PageContext pageContext;
    private final HttpServletRequest request;
    //private StoreFrontInterface storeFrontInterface;
    private final WeblisketSession weblisketSession;
    private String storeName;
    private StoreFrontInterface modifyingStoreFrontInterface;

    private final Portion portion;

    public StoreFrontsRequestHelper(HashMap hashMap, PageContext pageContext) throws Exception
    {
        this.pageContext = pageContext;
        this.request = (HttpServletRequest) pageContext.getRequest();
        this.weblisketSession = new WeblisketSession(hashMap, pageContext);

        this.portion = new Portion(hashMap);
        
        this.getFormData();

        //String storeName = (String) hashMap.get(StoreFrontData.NAME);

        //if (storeName != null)
        //{
            //this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
        //}
    }

    private void getFormData() throws Exception
    {
        //this.storeName = request.getParameter(StoreFrontData.NAME);
        //if(this.storeName==null)
        this.storeName = this.weblisketSession.getStoreName();
        this.modifyingStoreFrontInterface = (StoreFrontInterface) new StoreFront(this.request);
    }

    public String update()
    {
        try
        {
            String success = "Updated Successfully";
            HashMap hashMapData = this.modifyingStoreFrontInterface.toHashMap();
            StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().update(hashMapData);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "update()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to update storefronts table";

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "update()", e));
            }
            return error;
        }
    }

    public void sendStoreCreatedEmails() throws Exception
    {
        String storeManagerEmailSubject =
            "Store Manager Nofification For Store: "
            + this.modifyingStoreFrontInterface.getName();
        String adminEmailSubject = "Admin Notification For Store: "
            + this.modifyingStoreFrontInterface.getName();

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("Store Created: ");
        stringBuffer.append(this.modifyingStoreFrontInterface.getName());
        stringBuffer.append("\n\n");
        stringBuffer.append("Click here for the Admin Interface:\n");
        stringBuffer.append(this.modifyingStoreFrontInterface.getCurrentHomeHostName());
        stringBuffer.append("/admin/login.jsp\n\n");
        stringBuffer.append("Goto Your Store:\n");
        stringBuffer.append(this.modifyingStoreFrontInterface.getCurrentHomeHostName());
        stringBuffer.append(this.modifyingStoreFrontInterface.getName());
        stringBuffer.append("/index.jsp");
        stringBuffer.append("\n\n");
        stringBuffer.append("UserName: ");
        stringBuffer.append(this.weblisketSession.getUserName());

        String adminEmailTextBody = stringBuffer.toString();

        BasicEmailInfo adminBasicEmailInfo = (BasicEmailInfo) new AdminEmailInfo(adminEmailSubject, adminEmailTextBody);

        BasicEmailInfo storeAdminBasicEmailInfo = (BasicEmailInfo) new StoreEmailInfo(this.modifyingStoreFrontInterface,
            storeManagerEmailSubject, adminEmailTextBody);

        EmailInfo storeAdminEmailInfo = new EmailInfo(storeAdminBasicEmailInfo);

        EmailInfo adminEmailInfo = new EmailInfo(adminBasicEmailInfo);

        //Send response to Admin(s)
        UserEmailEventHandler adminUserEmailEventHandler =
            AdminUserEmailEventHandlerSingletons.getInstance(
            this.abeClientInformation, UserEmailEventNameData.STORECREATED);

        UserEmailEventHandler storeAdminUserEmailEventHandler =
            StoreAdminUserEmailEventHandlerSingletons.getInstance(
                UserEmailEventNameData.STORECREATED, this.abeClientInformation, this.modifyingStoreFrontInterface);

        storeAdminUserEmailEventHandler.receiveEmailInfo(
            UserEmailEventNameData.STORECREATED, storeAdminEmailInfo);
        adminUserEmailEventHandler.receiveEmailInfo(
            UserEmailEventNameData.STORECREATED, adminEmailInfo);
    }

    public synchronized String install()
    {
        try
        {
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("Intall StoreFront Successfully: ");
            stringBuffer.append(this.portion.getCurrent().intValue());
            stringBuffer.append(" of ");
            stringBuffer.append(this.portion.getTotal().intValue());

            String success = stringBuffer.toString();

            this.modifyingStoreFrontInterface.install(
            		this.portion.getCurrent().intValue(), 
            		this.portion.getTotal().intValue());

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "install()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to Install storefront";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "install()", e));
            }
            return error;
        }
    }

    public synchronized String insert()
    {
        try
        {
            String success = "Added Successfully";

            Vector values = this.modifyingStoreFrontInterface.toVector();

            StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().insert(values);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "insert()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to add storefront";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "insert()", e));
            }
            return error;
        }
    }

    public String delete()
    {
        try
        {
            String success = "Delete Successfully";
            StoreFrontsEntityFactory.getInstance().getStoreFrontsEntityInstance().delete(this.storeName);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
            {
                LogUtil.put(LogFactory.getInstance(success, this, "delete()"));
            }
            return success;
        } catch (Exception e)
        {
            String error = "Failed to delete storefronts table";
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
                LogUtil.put(LogFactory.getInstance(error, this, "delete()", e));
            }
            return error;
        }
    }
}
