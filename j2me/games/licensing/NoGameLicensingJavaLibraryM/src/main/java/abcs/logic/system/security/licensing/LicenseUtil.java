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

import javax.microedition.lcdui.CommandListener;

/**
 *
 * @author user
 */
public class LicenseUtil {

    private static final LicenseUtil instance = new LicenseUtil();

    public static LicenseUtil getInstance()
    {
        return instance;
    }

    /*
    public boolean isLicenseServerError(
            AbeLicenseInterface licenseInterface)
    {
        return false;
    }
     */

    public void init()
    {
    }

    public synchronized boolean isRunning()
    {
        return false;
    }

    public synchronized void setRunning(boolean running)
    {
    }
    
    public boolean shouldWait(CommandListener commandListener)
    {
        return this.isRunning();
    }

}
