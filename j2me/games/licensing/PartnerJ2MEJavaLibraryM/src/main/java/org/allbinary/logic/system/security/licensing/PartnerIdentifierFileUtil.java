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

import org.allbinary.string.CommonStrings;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.resource.ResourceUtil;

public class PartnerIdentifierFileUtil
{
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
            }

            inputStreamReader.close();

            return partnerString;
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().GET, e));

            return StringUtil.getInstance().NULL_STRING;
        }
    }
}
