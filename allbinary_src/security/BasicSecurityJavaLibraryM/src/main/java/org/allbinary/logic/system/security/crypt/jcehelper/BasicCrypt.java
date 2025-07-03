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
package org.allbinary.logic.system.security.crypt.jcehelper;

import org.allbinary.init.crypt.jcehelper.CryptInterface;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.java.byteutil.ByteUtil;
import org.allbinary.string.CommonStrings;

public class BasicCrypt implements CryptInterface
{
    private final ByteUtil byteUtil = ByteUtil.getInstance();

    private byte[] key;

    public BasicCrypt(String key)
    {
        try
        {
            this.key = key.getBytes();
        } catch (Exception e)
        {
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
            //{
            final CommonStrings commonStrings = CommonStrings.getInstance();
            PreLogUtil.put(commonStrings.EXCEPTION, this, "AbCrypt(alg,key)", e);
            //}
        }
    }

    public byte[] encrypt(byte[] array)
    {
        try
        {
            return this.mutilate(array);
        } catch (Exception e)
        {
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
            //{
            PreLogUtil.put("Encrypt Failed", this, "encrypt", e);
            //}
            return null;
        }
    }

    public byte[] decrypt(byte[] array)
    {
        try
        {
            return this.mutilate(array);
        } catch (Exception e)
        {
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
            //{
            PreLogUtil.put("decrypt Failed", this, "decrypt", e);
            //}
            return null;
        }
    }

    /*
    public byte[] mutilate(byte [] array)
    {
    ByteUtil byteUtil = new ByteUtil();
    array = byteUtil.not(array);
    return array;
    }
     */
    public byte[] mutilate(byte[] array)
    {
        for (int index = 0; index < key.length; index++)
        {
            byte val = key[index];
            //val = (byte) (val & (byte) 0x0F);
            //if(val < 8 && val >0)
            //{
            array = byteUtil.xor(array, val);
            //}
        }
        return array;
    }
}
