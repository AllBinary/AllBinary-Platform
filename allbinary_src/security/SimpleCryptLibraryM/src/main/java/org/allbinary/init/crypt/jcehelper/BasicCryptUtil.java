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
package org.allbinary.init.crypt.jcehelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

public class BasicCryptUtil
{
    private static final BasicCryptUtil instance = new BasicCryptUtil();

    /**
     * @return the instance
     */
    public static BasicCryptUtil getInstance()
    {
        return instance;
    }

    private final String XML_START = "<?xml";

    public InputStream getDecryptedInputStream(
            InputStream in, CryptInterface cryptInterface)
            throws Exception
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int ch;

        while ((ch = in.read()) != -1)
        {
            buffer.write((byte) ch);
        }

        //TWB - debug output
        //LogUtil.put(LogFactory.getInstance("Crypted: " + buffer.toString(), instance, "encRespXMLRPC"));
        //PreLogUtil.put("Crypted: " + buffer.toString(), instance, "encRespXMLRPC");

        //String responseData = buffer.toString();
        //responseData = responseData.substring(PHPCRYPTHEADER.length());
        //byte[] decrypted = cryptInterface.decrypt(responseData.getBytes());
        byte[] decrypted = cryptInterface.decrypt(buffer.toByteArray());

        //TWB - debug output
        String decryptedString = new String(decrypted);

        ////LogUtil.put(decryptedString, this, "decRespXMLRPC");
        //PreLogUtil.put(decryptedString, instance, "decRespXMLRPC");

        int index = decryptedString.indexOf(XML_START);
        if (index > 0)
        {
            //LogUtil.put(LogFactory.getInstance("Removing Pre Decrypted XML data", instance, "encRespXMLRPC"));
            ////System.out.println("Removing Pre Decrypted XML data");
            decryptedString = decryptedString.substring(index);
        }

        //LogUtil.put(LogFactory.getInstance(new String(decryptedString), instance, "decRespXMLRPC"));
        ////PreLogUtil.put(new String(decryptedString), this, "decRespXMLRPC");
        return new ByteArrayInputStream(decryptedString.getBytes());
    }
}
