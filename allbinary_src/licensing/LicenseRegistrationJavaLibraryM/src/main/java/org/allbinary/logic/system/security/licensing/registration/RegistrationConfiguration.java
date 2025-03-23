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
package org.allbinary.logic.system.security.licensing.registration;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.allbinary.logic.io.AbDataOutputStream;
import org.allbinary.logic.io.FileStreamFactory;
import org.allbinary.logic.io.StreamUtil;
import org.allbinary.logic.io.file.FileFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

public class RegistrationConfiguration
{
    private static final RegistrationConfiguration SINGLETON = new RegistrationConfiguration();

    private String registrationCode = "No Registration Code";

    public final String NAME = "registrationid";
    
    private final String FILE = "Registration.dat";

    private RegistrationConfiguration()
    {
        try
        {
            if(FileFactory.getInstance().isFile(FILE))
            {
                this.read();
            }
            else
            {
                this.write();
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this,
                    CommonStrings.getInstance().CONSTRUCTOR, e));
        }
    }

    public static RegistrationConfiguration getInstance()
    {
        return SINGLETON;
    }

    private void read() throws Exception
    {
        FileStreamFactory fileInputStreamFactory = 
            FileStreamFactory.getInstance();

        InputStream fileInputStream = fileInputStreamFactory
                .getFileInputStreamInstance(
                        StringUtil.getInstance().EMPTY_STRING, FILE);

        DataInputStream dataInputStream = 
            new DataInputStream(fileInputStream);

        this.setRegistrationCode(dataInputStream.readUTF());
        
        PreLogUtil.put(new StringMaker().append("Read Configuration: ").append(this.toString()).toString(), this, "read");
    }

    public void write() throws Exception
    {
        AbDataOutputStream dataOutputStream = null;
        try
        {
            
        LogUtil.put(LogFactory.getInstance(
                new StringMaker().append("Write Configuration: ").append(this.toString()).toString(), this, "write"));
        //PreLogUtil.put("Write Configuration: ").append(this.toString(), this, "write");
        
        FileStreamFactory fileInputStreamFactory = 
            FileStreamFactory.getInstance();

        OutputStream fileOutputStream = 
            fileInputStreamFactory
                .getFileOutputStreamInstance(
                        StringUtil.getInstance().EMPTY_STRING, FILE);
        
        dataOutputStream = 
            new AbDataOutputStream(fileOutputStream);

        dataOutputStream.writeUTF(this.getRegistrationCode());
        
        dataOutputStream.flush();
    }
    finally
    {
        StreamUtil.getInstance().close(dataOutputStream);
    }
        
    }

    public String toString()
    {
        return new StringMaker().append("Registration Code: ").append(this.getRegistrationCode()).toString();
    }

    public void setRegistrationCode(String registrationCode)
    {
        this.registrationCode = registrationCode;
    }

    public String getRegistrationCode()
    {
        return registrationCode;
    }
}
