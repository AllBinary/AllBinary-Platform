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
package org.allbinary.logic.communication.smtp.event.handler.factory;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.EmailEventHandlerUtil;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class AdminUserEmailEventHandlerSingletons {

    private static final AdminUserEmailEventHandlerSingletons instance =
        new AdminUserEmailEventHandlerSingletons();

    /**
     * @return the instance
     */
    public static AdminUserEmailEventHandlerSingletons getInstance() {
        return instance;
    }

    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    private HashMap userEmailEventHandlerHashMap = null;

    private AdminUserEmailEventHandlerSingletons() {
    }

    public UserEmailEventHandler getInstance(
        final AbeClientInformationInterface abeClientInformation, UserEmailEventNameData userEmailEventNameData)
        throws Exception {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "getInstance"));
        }

        if (this.userEmailEventHandlerHashMap == null) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
                LogUtil.put(LogFactory.getInstance("Initializing HashMap", this, "getInstance"));
            }

            this.userEmailEventHandlerHashMap = new HashMap();
        }

        UserEmailEventHandler userEmailEventHandler = (UserEmailEventHandler) this.userEmailEventHandlerHashMap.get(
            userEmailEventNameData);

        //Load Info if not found - check logic - logic is poor should load on null above
        if (userEmailEventHandler == null) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
                LogUtil.put(LogFactory.getInstance("Creating New Named UserEmailEventHandler", this, "getInstance"));
            }

            //Each store admin user my subscribe to emails with their email configuration
            Vector userVector = UserEntityFactory.getInstance().getAdministrators();

            //Create New Handler and add listeners
            UserEmailEventHandler newUserEmailEventHandler =
                EmailEventHandlerUtil.getEventHandler(
                    abeClientInformation, userEmailEventNameData, userVector);

            this.userEmailEventHandlerHashMap.put(
                userEmailEventNameData, newUserEmailEventHandler);

            return newUserEmailEventHandler;
        } else {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
                LogUtil.put(LogFactory.getInstance("Returning existing UserEmailEventHandler", this, "getInstance"));
            }

            //Return existing event Handler
            return userEmailEventHandler;
        }
    }
}
