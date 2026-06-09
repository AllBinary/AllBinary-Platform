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

import java.io.File;
import org.allbinary.TsUtil;
import org.allbinary.globals.PATH_GLOBALS;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.AbDataInputStream;
import org.allbinary.logic.io.AbDataOutputStream;
import org.allbinary.logic.io.AbFileInputStream;
import org.allbinary.logic.io.DataOutputStreamFactory;
import org.allbinary.logic.io.FileStreamFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.security.crypt.DatabaseEncoder;
import org.allbinary.logic.system.security.crypt.WeakCrypt;

public class LicenseInitInfoUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final LicenseInitInfoUtil instance = new LicenseInitInfoUtil();

    public static LicenseInitInfoUtil getInstance()
    {
        return LicenseInitInfoUtil.instance;
    }

    private final StringUtil stringUtil = StringUtil.getInstance();
    
    public final String INITFILENAME = "licenseinitdata.dat";
    public final String ABOUT = "about";
    public final String PRIVACY_POLICY = "privacy_policy";

    private String filePath = this.stringUtil.EMPTY_STRING;

    public synchronized void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public synchronized void write(LicenseInitInfo initData)
            throws Exception
    {
        if (this.filePath == this.stringUtil.EMPTY_STRING)
        {
            this.filePath = URLGLOBALS.getMainPath() + PATH_GLOBALS.getInstance().INIT_PATH;
        }

        try
        {
            final TsUtil tsUtil = TsUtil.getInstance();
            final AbDataOutputStream dataOutputStream =
                DataOutputStreamFactory.getInstance().getInstance(
                this.filePath, this.INITFILENAME);

            final byte[] licenseIdCrypted = tsUtil.getByteArray(new WeakCrypt(1).encrypt(
                    initData.getLicenseId()));

            dataOutputStream.writeUTF(DatabaseEncoder.encode(licenseIdCrypted));

            final int numberOfLicenseServers = initData.getNumberOfServers();
            dataOutputStream.writeInt(numberOfLicenseServers);

            byte[] licenseServerCrypted;
            for (int index = 0; index < numberOfLicenseServers; index++)
            {
                licenseServerCrypted = tsUtil.getByteArray(new WeakCrypt(3).encrypt(
                        initData.getServer(index)));
                dataOutputStream.writeUTF(DatabaseEncoder.encode(licenseServerCrypted));
            }
        }
        catch (Exception e)
        {
            // if
            // (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            // {
            this.logUtil.put("Command Failed: " + this.INITFILENAME, this, "write", e);
            // }
            FileStreamFactory.getInstance().delete(
                    this.filePath, this.INITFILENAME);

            throw e;
        }
    }

    public synchronized LicenseInitInfo read() throws Exception
    {
        return this.readAgain(0);
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

        if (this.filePath == this.stringUtil.EMPTY_STRING)
        {
            this.filePath = URLGLOBALS.getMainPath() + PATH_GLOBALS.getInstance().INIT_PATH;
        }

        try
        {
            // if
            // (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            // {
            // this.logUtil.put("LicenseInitInfo File: " +
            // FILEABPATH.toString(),
            this.logUtil.putF("LicenseInitInfo File: " + this.INITFILENAME, this, METHOD_NAME);
            // }

            //For testing a new platform file system
            //this.logUtil.putF("LicenseInitInfo isFile: " + new File(this.INITFILENAME).isFile(), this, METHOD_NAME);
            
            final FileStreamFactory fileStreamFactory = FileStreamFactory.getInstance();
            
            final AbFileInputStream iFile = fileStreamFactory.getFileInputStreamInstance(this.filePath, this.INITFILENAME);

            if (iFile != null)
            {

                final AbDataInputStream iData = new AbDataInputStream(iFile);
                final LicenseInitInfo initInfo = new LicenseInitInfo();

                byte[] decodedByteArray = DatabaseEncoder.decode(iData.readUTF());
                final String licenseIdDecoded = new String(decodedByteArray);

                initInfo.setLicenseId(new WeakCrypt(1).decrypt(licenseIdDecoded));

                int numberOfLicenseServers = iData.readInt();

                final String NEXT_FILE = "Next License Server From File: ";

                String licenseServerDecoded;

                for (int index = 0; index < numberOfLicenseServers; index++)
                {
                    decodedByteArray = DatabaseEncoder.decode(iData.readUTF());
                    licenseServerDecoded = new String(decodedByteArray);
                    initInfo.setServer(new WeakCrypt(3).decrypt(licenseServerDecoded), index);

                    // if
                    // (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
                    // {
                    this.logUtil.putF(NEXT_FILE + initInfo.getServer(index), this, METHOD_NAME);
                    // }
                }
                return initInfo;
            }
            else
            {
                throw new Exception("Could Not Load License InitInfo: " + this.INITFILENAME);
            }
        }
        catch (Exception e)
        {
            try
            {
                // if
                // (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRELOADER))
                // {
                this.logUtil.put("Command Failed: " + this.INITFILENAME, this, METHOD_NAME, e);
                // }

                //Thread.currentThread().sleep(2000);
//                Thread.sleep(2000);
//
//                // give up after 10tries
//                if (initializeCounter < 3)
//                {
//                    initializeCounter++;
//                    return readAgain(initializeCounter++);
//                }
            }
            catch (Exception e2)
            {
                // if
                // (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PRELOADERERROR))
                // {
                this.logUtil.put("LicenseInitInfo Read Retry: " + this.INITFILENAME, this, "readAgain()", e2);
                // }
            }
            throw new Exception("LicenseInitInfo Read Error: " + this.INITFILENAME);
        }
    }

    /**
     * @return the filePath
     */
    public String getFilePath()
    {
        return this.filePath;
    }
}
