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
package org.allbinary.logic.system.security.licensing;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class PartnerIdentifierFileUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final PartnerIdentifierFileUtil instance = 
        new PartnerIdentifierFileUtil();
    
    public static PartnerIdentifierFileUtil getInstance()
    {
        return instance;
    }

    public final String FILE_NAME = "partner.txt";
    
    public String get()
    {
        try
        {
            ResourceUtil resourceUtil = ResourceUtil.getInstance();
            
            InputStream inputStream = resourceUtil.getResourceAsStream(this.FILE_NAME);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            //BufferedReader bufferedReader =
            //  new BufferedReader(inputStreamReader);

            // String partnerString = bufferedReader.readLine();

            char[] chars = new char[30];

            int size = inputStreamReader.read(chars);

            String partnerString = StringUtil.getInstance().EMPTY_STRING;
            
            if(size > 0)
            {
                partnerString = String.valueOf(chars, 0, size);
                //partnerString= chars.concatToString(0, 0 + size)
                
            }

            inputStreamReader.close();

            return partnerString;
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET, e);

            return StringUtil.getInstance().NULL_STRING;
        }
    }
}
