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
package abcs.logic.system.security.licensing;

import abcs.logic.basic.string.CommonStrings;
import java.io.InputStream;
import java.io.InputStreamReader;

import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.resource.ResourceUtil;

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
