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
package abcs.logic.system.security.licensing;

import java.util.Hashtable;

import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringMaker;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.security.licensing.registration.RegistrationConfiguration;

public class MobileClientInformation 
    extends AbeClientInformation
{
    protected static final String DESC = "Mobile";
    protected static final String ANDROID_DESC = "Android" + DESC;

    public MobileClientInformation(String name, String version, String specialName)
    {
        super(name, version, 
                new StringMaker().append(specialName).
                append(CommonSeps.getInstance().SPACE).
                append(PartnerIdentifierFileUtil.getInstance().get()).toString());

        String number = this.getSpecialName().substring(this.getSpecialName().length() - 1);

        if(Integer.getInteger(number) == null)
        //if(this.getSpecialName().endsWith("nhs"))
        {
           this.setHardSale(false);
        }
        
        if (this.isHardSale())
        {
            LogUtil.put(LogFactory.getInstance("Use Hard Coded Selling", this, CommonStrings.getInstance().CONSTRUCTOR));
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("Don't Use Hard Coded Selling", this, CommonStrings.getInstance().CONSTRUCTOR));
        }
        
    }
    
    public Hashtable toHashtable()
    {
        Hashtable hashtable = super.toHashtable();
        
        RegistrationConfiguration registrationConfiguration = RegistrationConfiguration.getInstance();
        
        hashtable.put(registrationConfiguration.NAME, registrationConfiguration.getRegistrationCode());
        
        return hashtable;
    }
    
}
