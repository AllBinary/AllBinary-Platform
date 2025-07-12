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

import org.allbinary.globals.PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.AbDataInputStream;
import org.allbinary.logic.io.AbDataOutputStream;
import org.allbinary.logic.io.AbFileInputStream;
import org.allbinary.logic.io.DataOutputStreamFactory;
import org.allbinary.logic.io.FileStreamFactory;
import org.allbinary.logic.system.security.crypt.DatabaseEncoder;
import org.allbinary.logic.system.security.crypt.WeakCrypt;

public class LicenseInitInfoUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final LicenseInitInfoUtil instance = new LicenseInitInfoUtil();

    public static LicenseInitInfoUtil getInstance()
    {
        return instance;
    }

    public final String INITFILENAME = "licenseinitdata.dat";
    public final String ABOUT = "about";
    public final String PRIVACY_POLICY = "privacy_policy";

    private String filePath;

    public synchronized void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public synchronized void write(LicenseInitInfo initData)
            throws Exception
    {
        if (this.filePath == null)
        {
            this.filePath = URLGLOBALS.getMainPath() + PATH_GLOBALS.getInstance().INIT_PATH;
        }

        try
        {
            AbDataOutputStream dataOutputStream =
                DataOutputStreamFactory.getInstance().getInstance(
                this.filePath, INITFILENAME);

            byte[] licenseIdCrypted = new WeakCrypt(1).encrypt(
                    initData.getLicenseId()).getBytes();

            dataOutputStream.writeUTF(DatabaseEncoder.encode(licenseIdCrypted));

            int numberOfLicenseServers = initData.getNumberOfServers();
            dataOutputStream.writeInt(numberOfLicenseServers);

            for (int index = 0; index < numberOfLicenseServers; index++)
            {
                byte[] licenseServerCrypted = new WeakCrypt(3).encrypt(
                        initData.getServer(index)).getBytes();
                dataOutputStream.writeUTF(DatabaseEncoder.encode(licenseServerCrypted));
            }
        }
        catch (Exception e)
        {
            // if
            // (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            logUtil.put("Command Failed: " + INITFILENAME, this, "write", e);
            // }
            FileStreamFactory.getInstance().delete(
                    this.filePath, INITFILENAME);

            throw e;
        }
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
        final String METHOD_NAME = "readAgain";

        if (this.filePath == null)
        {
            this.filePath = URLGLOBALS.getMainPath() + PATH_GLOBALS.getInstance().INIT_PATH;
        }

        try
        {
            // if
            // (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            // logUtil.put("LicenseInitInfo File: " +
            // FILEABPATH.toString(),
            logUtil.put("LicenseInitInfo File: " + INITFILENAME, this, METHOD_NAME);
            // }

            FileStreamFactory fileStreamFactory = 
                FileStreamFactory.getInstance();
            
            AbFileInputStream iFile = fileStreamFactory
                    .getFileInputStreamInstance(this.filePath, INITFILENAME);

            if (iFile != null)
            {

                AbDataInputStream iData = new AbDataInputStream(iFile);
                LicenseInitInfo initInfo = new LicenseInitInfo();

                String licenseIdDecoded = new String(DatabaseEncoder.decode(iData.readUTF()));

                initInfo.setLicenseId(new WeakCrypt(1).decrypt(licenseIdDecoded));

                int numberOfLicenseServers = iData.readInt();

                final String NEXT_FILE = "Next License Server From File: ";

                String licenseServerDecoded;

                for (int index = 0; index < numberOfLicenseServers; index++)
                {
                    licenseServerDecoded = new String(DatabaseEncoder.decode(iData.readUTF()));
                    initInfo.setServer(new WeakCrypt(3).decrypt(licenseServerDecoded), index);

                    // if
                    // (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
                    // {
                    logUtil.put(NEXT_FILE + initInfo.getServer(index), this, METHOD_NAME);
                    // }
                }
                return initInfo;
            }
            else
            {
                throw new Exception("Could Not Load License InitInfo: " + INITFILENAME);
            }
        }
        catch (Exception e)
        {
            try
            {
                // if
                // (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRELOADER))
                // {
                logUtil.put("Command Failed: " + INITFILENAME, this, METHOD_NAME, e);
                // }

                Thread.currentThread().sleep(2000);

                // give up after 10tries
                if (initializeCounter < 3)
                {
                    initializeCounter++;
                    return readAgain(initializeCounter++);
                }
            }
            catch (Exception se)
            {
                // if
                // (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRELOADERERROR))
                // {
                logUtil.put("LicenseInitInfo Read Retry: " + INITFILENAME, this, "readAgain()", se);
                // }
            }
            throw new Exception("LicenseInitInfo Read Error: " + INITFILENAME);
        }
    }

    /**
     * @return the filePath
     */
    public String getFilePath()
    {
        return filePath;
    }
}
