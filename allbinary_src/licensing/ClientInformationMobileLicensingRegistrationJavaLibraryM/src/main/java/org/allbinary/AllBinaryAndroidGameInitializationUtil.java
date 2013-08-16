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
