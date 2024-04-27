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
package org.allbinary.business.init;

public class LicenseInitInfoUtil
{
    private static final LicenseInitInfoUtil instance = new LicenseInitInfoUtil();

    public static LicenseInitInfoUtil getInstance()
    {
        return instance;
    }

    public final String INITFILENAME = "licenseinitdata.dat";

    private final String OFFICIAL_ALLBINARY_SERVICES_SERVER = "https://services.allbinary.com/LicServ/serverssl.php";

    public synchronized void setFilePath(String filePath)
    {
    }

    private LicenseInitInfoUtil()
    {
    }

    public synchronized void write(LicenseInitInfo initData)
            throws Exception
    {
    }

    public synchronized LicenseInitInfo read() throws Exception
    {
        return readAgain(0);
    }

    //This does not work on Android LOL
    /*
    public static synchronized boolean exists() throws Exception
    {
        AbPath abPath = new AbPath(URLGLOBALS.getMainPath() + PATH_GLOBALS.INIT_PATH,
                INITFILENAME);
        
        return FileUtil.isFile(abPath);
    }
    */

    public synchronized LicenseInitInfo readAgain(int initializeCounter)
            throws Exception
    {
        LicenseInitInfo licenseInitInfo = new LicenseInitInfo();
        licenseInitInfo.clearServers();

        //Add your servers here
        licenseInitInfo.addServer(OFFICIAL_ALLBINARY_SERVICES_SERVER);

        return licenseInitInfo;
    }
}