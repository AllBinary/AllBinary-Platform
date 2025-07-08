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
import org.allbinary.business.user.UserInterface;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.EmailEventHandlerUtil;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.communication.smtp.event.modules.log.LogUserEmailEventListenerModule;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;

public class UserEmailEventHandlerSingletons {

    private static final UserEmailEventHandlerSingletons instance =
        new UserEmailEventHandlerSingletons();

    /**
     * @return the instance
     */
    public static UserEmailEventHandlerSingletons getInstance() {
        return instance;
    }
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    private final HashMap userEmailEventHandlerHashMap = new HashMap();

    private UserEmailEventHandlerSingletons() {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
            LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.CONSTRUCTOR));
        }
    }

    public UserEmailEventHandler getInstance(
        final AbeClientInformationInterface abeClientInformation,
        UserEmailEventNameData userEmailEventNameData,
        UserInterface userInterface)
        throws Exception {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, commonStrings.GET_INSTANCE));
        }

        UserEmailEventHandler userEmailEventHandler = (UserEmailEventHandler) this.userEmailEventHandlerHashMap.get(
            userEmailEventNameData);

        if (userEmailEventHandler == null) {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
                LogUtil.put(LogFactory.getInstance("Creating New Named UserEmailEventHandler", this, commonStrings.GET_INSTANCE));
            }

            //Create New Handler and add listeners
            UserEmailEventHandler newUserEmailEventHandler = new UserEmailEventHandler();

            Vector vector = EmailEventHandlerUtil.getUserEmailEventListenerVector(
                abeClientInformation, userEmailEventNameData, userInterface);

            newUserEmailEventHandler.addListener(vector);
            newUserEmailEventHandler.addListener(new LogUserEmailEventListenerModule());

            this.userEmailEventHandlerHashMap.put(
                userEmailEventNameData, newUserEmailEventHandler);

            return newUserEmailEventHandler;
        } else {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
                LogUtil.put(LogFactory.getInstance("Returning existing UserEmailEventHandler", this, commonStrings.GET_INSTANCE));
            }

            //Return existing event Handler
            return userEmailEventHandler;
        }
    }

}
