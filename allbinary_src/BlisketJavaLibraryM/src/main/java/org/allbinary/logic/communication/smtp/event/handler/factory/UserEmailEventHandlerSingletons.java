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
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.business.user.UserInterface;
import org.allbinary.logic.StdUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.EmailEventHandlerUtil;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.communication.smtp.event.modules.log.LogUserEmailEventListenerModule;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

public class UserEmailEventHandlerSingletons {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final UserEmailEventHandlerSingletons instance =
        new UserEmailEventHandlerSingletons();

    /**
     * @return the instance
     */
    public static UserEmailEventHandlerSingletons getInstance() {
        return UserEmailEventHandlerSingletons.instance;
    }
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    private final HashMap userEmailEventHandlerHashMap = StdUtil.getInstance().createHashMap();

    private UserEmailEventHandlerSingletons() {
        if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
            this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);
        }
    }

    public UserEmailEventHandler getInstance(
        final AbeClientInformationInterface abeClientInformation,
        UserEmailEventNameData userEmailEventNameData,
        UserInterface userInterface)
        throws Exception {
        if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
            this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.GET_INSTANCE);
        }

        UserEmailEventHandler userEmailEventHandler = (UserEmailEventHandler) this.userEmailEventHandlerHashMap.get(
            userEmailEventNameData);

        if (userEmailEventHandler == null) {
            if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
                this.logUtil.putF("Creating New Named UserEmailEventHandler", this, this.commonStrings.GET_INSTANCE);
            }

            //Create New Handler and add listeners
            UserEmailEventHandler newUserEmailEventHandler = new UserEmailEventHandler();

            BasicArrayList vector = EmailEventHandlerUtil.getUserEmailEventListenerVector(
                abeClientInformation, userEmailEventNameData, userInterface);

            newUserEmailEventHandler.addListener(vector);
            newUserEmailEventHandler.addListener(new LogUserEmailEventListenerModule());

            this.userEmailEventHandlerHashMap.put(
                userEmailEventNameData, newUserEmailEventHandler);

            return newUserEmailEventHandler;
        } else {
            if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().EMAILLOGGING)) {
                this.logUtil.putF("Returning existing UserEmailEventHandler", this, this.commonStrings.GET_INSTANCE);
            }

            //Return existing event Handler
            return userEmailEventHandler;
        }
    }

}
