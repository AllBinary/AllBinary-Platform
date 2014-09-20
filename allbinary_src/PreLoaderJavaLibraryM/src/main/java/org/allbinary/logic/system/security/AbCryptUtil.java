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
package org.allbinary.logic.system.security;

import org.allbinary.logic.basic.io.StreamUtil;
import org.allbinary.logic.system.security.crypt.jcehelper.AbCrypt;
import org.allbinary.logic.system.security.crypt.jcehelper.KeySpecFactory;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AbCryptUtil
{
    
    private AbCryptUtil()
    {
    }
    
    public static byte[] decrypt(InputStream inputStream, String key)
    throws Exception
    {
    	ByteArrayOutputStream outputStream = null;
    	
    	try
    	{
    		outputStream = (ByteArrayOutputStream)
    		StreamUtil.getInstance().get(inputStream, new ByteArrayOutputStream());

        AbCrypt abCrypt = new AbCrypt(KeySpecFactory.getInstance().DESEDE, key);
        return abCrypt.decrypt(outputStream.toByteArray());
        } 
        finally
        {
        	StreamUtil.getInstance().close(outputStream);
        	StreamUtil.getInstance().close(inputStream);
        }
    }
}
