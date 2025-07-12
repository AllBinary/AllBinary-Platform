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
package org.allbinary;

import org.allbinary.business.init.LicenseInitInfoUtil;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.system.security.licensing.PartnerIdentifierFileUtil;

public class AllBinaryAndroidGameInitializationUtil {

    public static void init()
    {
        final ResourceUtil resourceUtil = ResourceUtil.getInstance();

        final AndroidResources androidResources = AndroidResources.getInstance();
        
        final LicenseInitInfoUtil licenseInitInfoUtil = LicenseInitInfoUtil.getInstance();
        
        resourceUtil.addResource(
                licenseInitInfoUtil.INITFILENAME,
                Integer.valueOf(androidResources.raw.licenseinitdata)
                );

        resourceUtil.addResource(
                PartnerIdentifierFileUtil.getInstance().FILE_NAME,
                Integer.valueOf(androidResources.raw.partner)
                );
        
    }
	
}
