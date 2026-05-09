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

import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class BasicGameResources
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected static final StringMaker stringBuffer = new StringMaker();
    
    public String RESOURCE = StringUtil.getInstance().EMPTY_STRING;
    public String RESOURCE_DESTROY = StringUtil.getInstance().EMPTY_STRING;

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
                this.append(ROOT, SIZE[scale - 1]);
            } else
            {
                throw new Exception(new StringMaker().append("Invalid Value: ").appendint(scale).toString());
            }

            //this.logUtil.putF("Resource: ").append(this.RESOURCE).append(" Destroy: ").append(this.RESOURCE_DESTROY, this, this.commonStrings.INIT);
        } catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
        }
    }
    
    protected void append(String ROOT, String sizeString)
        throws Exception
    {
        final String DESTROY = "_destroy";

        final String string = this.getString();
        
        BasicGameResources.stringBuffer.delete(0, BasicGameResources.stringBuffer.length());
        BasicGameResources.stringBuffer.append(ROOT);
        BasicGameResources.stringBuffer.append(string);
        BasicGameResources.stringBuffer.append(sizeString);

        this.RESOURCE = BasicGameResources.stringBuffer.toString();
        
        BasicGameResources.stringBuffer.delete(0, BasicGameResources.stringBuffer.length());
        BasicGameResources.stringBuffer.append(ROOT);
        BasicGameResources.stringBuffer.append(DESTROY);
        BasicGameResources.stringBuffer.append(string);
        BasicGameResources.stringBuffer.append(sizeString);
        
        this.RESOURCE_DESTROY = BasicGameResources.stringBuffer.toString();
    }

    protected String getString()
            throws Exception
    {
        return GameGraphicsResourceUtil.getInstance().getName();
    }

}
