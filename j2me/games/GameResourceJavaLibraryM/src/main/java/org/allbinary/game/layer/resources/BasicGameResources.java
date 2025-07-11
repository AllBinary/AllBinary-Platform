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
package org.allbinary.game.layer.resources;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.GameConfigurationCentral;

public class BasicGameResources
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final StringMaker stringBuffer = new StringMaker();
    
    public String RESOURCE;
    public String RESOURCE_DESTROY;

    protected BasicGameResources()
    {
    }

    public void init(String ROOT, String[] SIZE)
    {
        try
        {
            int scale = GameConfigurationCentral.getInstance().SCALE.getValue().intValue();

            if (scale > 1 && scale - 1 < SIZE.length)
            {
                this.init(ROOT, SIZE[scale - 1]);
            } else
            {
                throw new Exception(new StringMaker().append("Invalid Value: ").append(scale).toString());
            }

            //logUtil.put("Resource: ").append(this.RESOURCE).append(" Destroy: ").append(this.RESOURCE_DESTROY, this, commonStrings.INIT);
        } catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
        }
    }

    protected String getString()
        throws Exception
    {
        return GameGraphicsResourceUtil.getInstance().getName();
    }
    
    protected void init(String ROOT, String sizeString) 
        throws Exception
    {
        final String DESTROY = "_destroy";

        String string = this.getString();
        
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(ROOT);
        stringBuffer.append(string);
        stringBuffer.append(sizeString);

        this.RESOURCE = stringBuffer.toString();
        
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(ROOT);
        stringBuffer.append(DESTROY);
        stringBuffer.append(string);
        stringBuffer.append(sizeString);
        
        this.RESOURCE_DESTROY = stringBuffer.toString();
    }
}
