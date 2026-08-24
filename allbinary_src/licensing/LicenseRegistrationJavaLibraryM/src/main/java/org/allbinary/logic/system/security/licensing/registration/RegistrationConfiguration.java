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

import jsinterop.annotations.JsType;

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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class RegistrationConfiguration
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final RegistrationConfiguration SINGLETON = new RegistrationConfiguration();

    private String registrationCode = "No Registration Code";

    @JsProperty
    public final String NAME = "registrationid";
    
    private final String FILE = "Registration.dat";

    @JsConstructor
    private RegistrationConfiguration()
    {
        try
        {
            if(FileFactory.getInstance().isFile(this.FILE))
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
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this,commonStrings.CONSTRUCTOR, e);
        }
    }

    @JsMethod
    public static RegistrationConfiguration getInstance()
    {
        return RegistrationConfiguration.SINGLETON;
    }

    @JsMethod
    private void read() throws Exception
    {
        FileStreamFactory fileInputStreamFactory = 
            FileStreamFactory.getInstance();

        InputStream fileInputStream = fileInputStreamFactory
                .getFileInputStreamInstance(
                        StringUtil.getInstance().EMPTY_STRING, this.FILE);

        DataInputStream dataInputStream = 
            new DataInputStream(fileInputStream);

        this.setRegistrationCode(dataInputStream.readUTF());
        
        PreLogUtil.put(new StringMaker().append("Read Configuration: ").append(this.toString()).toString(), this, "read");
    }

    @JsMethod
    public void write() throws Exception
    {
        AbDataOutputStream dataOutputStream = null;
        try
        {
            
        this.logUtil.putF(
                new StringMaker().append("Write Configuration: ").append(this.toString()).toString(), this, "write");
        //PreLogUtil.put("Write Configuration: ").append(this.toString(), this, "write");
        
        FileStreamFactory fileInputStreamFactory = 
            FileStreamFactory.getInstance();

        OutputStream fileOutputStream = 
            fileInputStreamFactory
                .getFileOutputStreamInstance(
                        StringUtil.getInstance().EMPTY_STRING, this.FILE);
        
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

    @JsMethod
    public String toString()
    {
        return new StringMaker().append("Registration Code: ").append(this.getRegistrationCode()).toString();
    }

    @JsMethod
    public void setRegistrationCode(String registrationCode)
    {
        this.registrationCode = registrationCode;
    }

    @JsMethod
    public String getRegistrationCode()
    {
        return this.registrationCode;
    }
}
