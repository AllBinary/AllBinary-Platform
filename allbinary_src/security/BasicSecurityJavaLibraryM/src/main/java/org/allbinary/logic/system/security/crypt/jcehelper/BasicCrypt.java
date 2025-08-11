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
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.java.byteutil.ByteUtil;
import org.allbinary.string.CommonStrings;

public class BasicCrypt implements CryptInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final ByteUtil byteUtil = ByteUtil.getInstance();

    private final byte[] key;

    public BasicCrypt(String keyAsString)
    {
        byte[] key = NullUtil.getInstance().NULL_BYTE_ARRAY;
        try
        {
            key = keyAsString.getBytes();
        } catch (Exception e)
        {
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
            //{
            final CommonStrings commonStrings = CommonStrings.getInstance();
            PreLogUtil.put(commonStrings.EXCEPTION, this, "AbCrypt(alg,key)", e);
            //}
        }
        this.key = key;
    }

    @Override
    public byte[] encrypt(final byte[] array)
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
            return NullUtil.getInstance().NULL_BYTE_ARRAY;
        }
    }

    @Override
    public byte[] decrypt(final byte[] array)
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
            return NullUtil.getInstance().NULL_BYTE_ARRAY;
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
        byte value;
        for (int index = 0; index < key.length; index++)
        {
            value = key[index];
            //val = (byte) (val & (byte) 0x0F);
            //if(val < 8 && val >0)
            //{
            array = byteUtil.xor(array, value);
            //}
        }
        return array;
    }
}
