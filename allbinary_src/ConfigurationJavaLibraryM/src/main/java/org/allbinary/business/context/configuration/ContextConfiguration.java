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
package org.allbinary.business.context.configuration;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.smtp.configuration.server.EmailServerConfiguration;
import org.allbinary.logic.communication.smtp.configuration.server.EmailServerConfigurationInterface;

import java.util.HashMap;
import org.allbinary.string.CommonStrings;

public class ContextConfiguration implements ContextConfigurationInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private EmailServerConfigurationInterface emailServerConfigurationInterface;

    public ContextConfiguration()
    {
        logUtil.put(commonStrings.START, this, "ContextConfiguration");

        this.setEmailServerConfigurationInterface((EmailServerConfigurationInterface) new EmailServerConfiguration());
    }

    public ContextConfiguration(HashMap hashMap)
    {
        logUtil.put(commonStrings.START, this, "ContextConfiguration(HashMap)");

        this.setEmailServerConfigurationInterface((EmailServerConfigurationInterface) new EmailServerConfiguration(hashMap));
    }

    public EmailServerConfigurationInterface getEmailServerConfigurationInterface()
    {
        return emailServerConfigurationInterface;
    }

    public void setEmailServerConfigurationInterface(EmailServerConfigurationInterface emailServerConfigurationInterface)
    {
        this.emailServerConfigurationInterface = emailServerConfigurationInterface;
    }
}
