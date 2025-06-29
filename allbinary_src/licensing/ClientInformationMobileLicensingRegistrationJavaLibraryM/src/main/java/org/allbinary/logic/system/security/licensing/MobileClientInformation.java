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
package org.allbinary.logic.system.security.licensing;

import org.allbinary.logic.system.security.licensing.PartnerIdentifierFileUtil;
import java.util.Hashtable;

import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.registration.RegistrationConfiguration;

public class MobileClientInformation 
    extends AbeClientInformation
{
    protected static final String DESC = "Mobile";
    protected static final String ANDROID_DESC = "Android" + DESC;

    public MobileClientInformation(final String name, final String version, final String specialName, final String shortName)
    {
        super(name, version, 
                new StringMaker().append(specialName).
                append(CommonSeps.getInstance().SPACE).
                append(PartnerIdentifierFileUtil.getInstance().get()).toString(),
                shortName);

        final String number = this.getSpecialName().substring(this.getSpecialName().length() - 1);

        if(Integer.getInteger(number) == null)
        //if(this.getSpecialName().endsWith("nhs"))
        {
           this.setHardSale(false);
        }
        
        if (this.isHardSale())
        {
            LogUtil.put(LogFactory.getInstance("Use Hard Coded Selling", this, commonStrings.CONSTRUCTOR));
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("Don't Use Hard Coded Selling", this, commonStrings.CONSTRUCTOR));
        }
        
    }
    
    public Hashtable toHashtable()
    {
        final Hashtable hashtable = super.toHashtable();
        
        final RegistrationConfiguration registrationConfiguration = RegistrationConfiguration.getInstance();
        
        hashtable.put(registrationConfiguration.NAME, registrationConfiguration.getRegistrationCode());
        
        return hashtable;
    }
    
}
