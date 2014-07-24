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

import abcs.business.init.LicenseInitInfoUtil;
import abcs.logic.system.security.licensing.PartnerIdentifierFileUtil;
import allbinary.data.resource.ResourceUtil;

public class AllBinaryAndroidGameInitializationUtil {

    public static void init()
    {
        ResourceUtil resourceUtil = ResourceUtil.getInstance();

        resourceUtil.addResource(
                LicenseInitInfoUtil.getInstance().INITFILENAME,
                new Integer(AndroidResources.raw.licenseinitdata)
                );

        resourceUtil.addResource(
                PartnerIdentifierFileUtil.getInstance().FILE_NAME,
                new Integer(AndroidResources.raw.partner)
                );
    }
	
}
