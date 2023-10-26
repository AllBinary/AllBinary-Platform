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

/*
 User Database
 Set Name: securedc_user
 Set UserName: securedc_cutome
 Set Password: user835
 
 History Database
 Set Name: securedc_history
 Set UserName: securedc_history
 Set Password: history835
 
 Log Database
 Set Name: securedc_securelog
 Set UserName: securedc_securel
 Set Password: securesite835
 
 StaticPages Database
 securedc_static
 securedc_static
 securesite835
 
 Inventory Database
 securedc_inventory
 securedc_invento
 inventory835
 */
package org.allbinary.business.init.db;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.basic.io.AbDataInputStream;
import org.allbinary.logic.basic.io.AbDataOutputStream;
import org.allbinary.logic.basic.io.AbFileInputStream;
import org.allbinary.logic.basic.io.DataOutputStreamFactory;
import org.allbinary.logic.basic.io.StreamUtil;
import org.allbinary.logic.basic.io.file.AbFile;
import org.allbinary.logic.basic.path.AbPath;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.system.security.crypt.DatabaseEncoder;
import org.allbinary.logic.system.security.crypt.WeakCrypt;
import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.logic.basic.io.file.directory.Directory;
import org.allbinary.logic.communication.http.HttpData;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

public class DbInitInfo extends DbConnectionInfo
{
    private final HttpData httpData = HttpData.getInstance();

    private final String GET_URL = "getUrl";

    //private final String PACKAGE = "/allbinary/ecommerce/init/db/";
    private final String PACKAGE = FREEBLISKET_PATH_GLOBALS.getInstance().DBINITPATH;

    private String initFileName;

    private boolean hasRead = false;

    //read argument is off for installation only
    public DbInitInfo(String initFileName, boolean read)
    {
        super();
        this.initFileName = initFileName;
        if(read)
        {
            this.updateIfNeeded();
        }else
        {
            this.setHasRead(true);
        }
    }

    public String getUrl()
    {
        String url = super.getUrl();
          //this.getHost() + this.getName() +
        //this.getUserNameKey() + this.getUserName() +
        //this.getPasswordKey() + this.getPassword();

        if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADER))
        {
            PreLogUtil.put(this.httpData.URL_LABEL + url, this, GET_URL);
        }
        return url;
    }

    public synchronized void write() throws Exception
    {
        
        AbPath PATH = new AbPath(URLGLOBALS.getWebappPath() + PACKAGE);
        Directory.create(PATH);
        AbPath FILEABPATH = new AbPath(URLGLOBALS.getWebappPath() + PACKAGE, this.initFileName);
        try
        {
            AbFile newFile = new AbFile(FILEABPATH);

            newFile.createNewFile();

         //PreLogUtil.put("Host: " + this.getHost(), this, "write");
            AbDataOutputStream dataOutputStream
                    = DataOutputStreamFactory.getInstance().getInstance(newFile);

            try
            {

                byte[] cryptedJdbcDriver = new WeakCrypt(1).encrypt(this.getJdbcDriver()).getBytes();

                byte[] cryptedName = new WeakCrypt(2).encrypt(this.getName()).getBytes();
                byte[] cryptedUserName = new WeakCrypt(3).encrypt(this.getUserName()).getBytes();
                byte[] cryptedPassword = new WeakCrypt(4).encrypt(this.getPassword()).getBytes();
                byte[] cryptedSchema = new WeakCrypt(5).encrypt(this.getSchema()).getBytes();
                byte[] cryptedServer = new WeakCrypt(6).encrypt(this.getServer()).getBytes();
                byte[] cryptedPort = new WeakCrypt(7).encrypt(this.getPort()).getBytes();

                dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedJdbcDriver));

                dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedName));
                dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedUserName));
                dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedPassword));
                dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedSchema));
                dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedServer));
                dataOutputStream.writeUTF(DatabaseEncoder.encode(cryptedPort));
                hasRead = false;

            }finally
            {
                StreamUtil.getInstance().close(dataOutputStream);
            }
        }catch(Exception e)
        {
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
            {
                PreLogUtil.put("Failed Write: " + FILEABPATH.toString(), this, "write", e);
            }
        }
    }

    private synchronized void load() throws Exception
    {
        AbPath FILEABPATH
                = new AbPath(URLGLOBALS.getWebappPath() + PACKAGE, this.initFileName);
        try
        {
            AbFile file = new AbFile(FILEABPATH);
            if(file.isFile())
            {
                AbFileInputStream iFile = new AbFileInputStream(file);
                AbDataInputStream iData = new AbDataInputStream(iFile);

                try
                {
                    String decryptedJdbcDriver = new String(DatabaseEncoder.decode(iData.readUTF()));

                    String decryptedName = new String(DatabaseEncoder.decode(iData.readUTF()));
                    String decryptedUserName = new String(DatabaseEncoder.decode(iData.readUTF()));
                    String decryptedPassword = new String(DatabaseEncoder.decode(iData.readUTF()));
                    String decryptedSchema = new String(DatabaseEncoder.decode(iData.readUTF()));
                    String decryptedServer = new String(DatabaseEncoder.decode(iData.readUTF()));
                    String decryptedPort = new String(DatabaseEncoder.decode(iData.readUTF()));

                    this.setJdbcDriver(new WeakCrypt(1).decrypt(decryptedJdbcDriver));
                    this.setName(new WeakCrypt(2).decrypt(decryptedName));
                    this.setUserName(new WeakCrypt(3).decrypt(decryptedUserName));
                    this.setPassword(new WeakCrypt(4).decrypt(decryptedPassword));
                    this.setSchema(new WeakCrypt(5).decrypt(decryptedSchema));
                    this.setServer(new WeakCrypt(6).decrypt(decryptedServer));
                    this.setPort(new WeakCrypt(7).decrypt(decryptedPort));

            //this.setJdbcDriver("org.jiql.jdbc.Driver");
                    //this.setName(this.getClass().getName());
                    //this.setUserName("admin");
                    //this.setPassword("jiql");
                    //this.setSchema("jdbc:jiql");
                    //this.setServer("local");
                    //this.setPort("");
                }finally
                {
                    StreamUtil.getInstance().close(iData);
                }

                //PreLogUtil.put("Host: " + this.getHost(), this, "load");
            }else
            {
                hasRead = false;
                if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADER))
                {
                    PreLogUtil.put("Not a File - Failed Loading: " + FILEABPATH.toString(), this, "load");
                }
            }
        }catch(Exception e)
        {
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
            {
                PreLogUtil.put("Failed Loading: " + FILEABPATH.toString(), this, "load", e);
            }
        }
    }

    public synchronized void setHasRead(boolean value)
    {
        this.hasRead = value;
    }

    private synchronized void updateIfNeeded()
    {
        try
        {
            if(!hasRead)
            {
                hasRead = true;
                this.load();
            }
        }catch(Exception e)
        {
            if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
            {
                PreLogUtil.put("Failed", this, "updateIfNeeded", e);
            }
        }
    }

    public String getName()
    {
        this.updateIfNeeded();

        if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADER))
        {
            PreLogUtil.put("Name: " + super.getName(), this, "getName");
        }

        return super.getName();
    }

    public String getUserName()
    {
        this.updateIfNeeded();

        if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADER))
        {
            PreLogUtil.put("Name: " + super.getUserName(), this, "getUserName");
        }

        return super.getUserName();
    }

    public String getPassword()
    {
        this.updateIfNeeded();
        return super.getPassword();
    }

    public String getSchema()
    {
        this.updateIfNeeded();
        return super.getSchema();
    }

    public String getServer()
    {
        this.updateIfNeeded();
        return super.getServer();
    }

    public String getPort()
    {
        this.updateIfNeeded();
        return super.getPort();
    }
}
