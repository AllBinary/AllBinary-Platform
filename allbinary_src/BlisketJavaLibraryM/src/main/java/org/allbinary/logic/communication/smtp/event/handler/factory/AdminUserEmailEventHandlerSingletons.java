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

import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.EmailEventHandlerUtil;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class AdminUserEmailEventHandlerSingletons {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final AdminUserEmailEventHandlerSingletons instance =
        new AdminUserEmailEventHandlerSingletons();

    /**
     * @return the instance
     */
    public static AdminUserEmailEventHandlerSingletons getInstance() {
        return instance;
    }

    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    private final HashMap userEmailEventHandlerHashMap = new HashMap();

    private AdminUserEmailEventHandlerSingletons() {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
            logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
        }
    }

    public UserEmailEventHandler getInstance(
        final AbeClientInformationInterface abeClientInformation, UserEmailEventNameData userEmailEventNameData)
        throws Exception {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
            logUtil.put(this.commonStrings.START, this, commonStrings.GET_INSTANCE);
        }

        UserEmailEventHandler userEmailEventHandler = (UserEmailEventHandler) this.userEmailEventHandlerHashMap.get(
            userEmailEventNameData);

        //Load Info if not found - check logic - logic is poor should load on null above
        if (userEmailEventHandler == null) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
                logUtil.put("Creating New Named UserEmailEventHandler", this, commonStrings.GET_INSTANCE);
            }

            //Each store admin user my subscribe to emails with their email configuration
            Vector userVector = UserEntityFactory.getInstance().getAdministrators();

            //Create New Handler and add listeners
            UserEmailEventHandler newUserEmailEventHandler =
                EmailEventHandlerUtil.getInstance().getEventHandler(
                    abeClientInformation, userEmailEventNameData, userVector);

            this.userEmailEventHandlerHashMap.put(
                userEmailEventNameData, newUserEmailEventHandler);

            return newUserEmailEventHandler;
        } else {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
                logUtil.put("Returning existing UserEmailEventHandler", this, commonStrings.GET_INSTANCE);
            }

            //Return existing event Handler
            return userEmailEventHandler;
        }
    }
}
