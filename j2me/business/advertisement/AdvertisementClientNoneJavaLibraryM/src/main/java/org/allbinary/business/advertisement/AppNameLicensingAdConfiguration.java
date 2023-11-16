package org.allbinary.business.advertisement;

import org.allbinary.logic.string.StringUtil;

public class AppNameLicensingAdConfiguration extends AdConfiguration
{
    public AppNameLicensingAdConfiguration()
    {
        super(new Object[] {
            StringUtil.getInstance().EMPTY_STRING,
            StringUtil.getInstance().EMPTY_STRING,
            StringUtil.getInstance().EMPTY_STRING
        });
    }
}
