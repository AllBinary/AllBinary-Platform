/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer.unit;

import org.allbinary.game.layer.resources.BasicGameResources;
import org.allbinary.game.layer.resources.GameGraphicsResourceUtil;
import org.allbinary.AndroidUtil;
import org.allbinary.game.configuration.feature.GraphicsFeatureFactory;
import org.allbinary.logic.string.StringUtil;

public class UnitGameResources extends BasicGameResources
{
    public String NAME = StringUtil.getInstance().EMPTY_STRING;
    //public String RESOURCE_EMPTY;
    //public String RESOURCE_BASE;

    public String RESOURCE_ICON_ANIMATION = StringUtil.getInstance().EMPTY_STRING;
    public String RESOURCE_ICON_BUILD = StringUtil.getInstance().EMPTY_STRING;
    public String RESOURCE_ICON = StringUtil.getInstance().EMPTY_STRING;

    protected UnitGameResources()
    {
    }

    private final static String ICON = "_icon";
    private final static String BUILD = "_build";
    private final static String DESTROY = "_destroy";
    private final static String ICON_BUILD = "_icon" + UnitGameResources.BUILD;
    //private final static String BASE = "_base";
    //private final static String EMPTY = "_empty";

    private final String ICON_SIZE = "_64_by_64.png";
    
    @Override
    protected void append(String ROOT, String sizeString) throws Exception
    {
        super.append(ROOT, sizeString);

        String string = GameGraphicsResourceUtil.getInstance().getName();

        BasicGameResources.stringBuffer.delete(0, BasicGameResources.stringBuffer.length());
        BasicGameResources.stringBuffer.append(ROOT);
        BasicGameResources.stringBuffer.append(UnitGameResources.DESTROY);
        BasicGameResources.stringBuffer.append(string);
        BasicGameResources.stringBuffer.append(sizeString);

        this.RESOURCE_DESTROY = BasicGameResources.stringBuffer.toString();
        
        BasicGameResources.stringBuffer.delete(0, BasicGameResources.stringBuffer.length());
        BasicGameResources.stringBuffer.append(UnitGameResources.ICON);
        BasicGameResources.stringBuffer.append(ROOT);

        this.RESOURCE_ICON_ANIMATION = BasicGameResources.stringBuffer.toString();

        BasicGameResources.stringBuffer.delete(0, BasicGameResources.stringBuffer.length());
        BasicGameResources.stringBuffer.append(ROOT);
        BasicGameResources.stringBuffer.append(UnitGameResources.ICON);
        BasicGameResources.stringBuffer.append(string);
        BasicGameResources.stringBuffer.append(this.ICON_SIZE);

        this.RESOURCE_ICON = BasicGameResources.stringBuffer.toString();

        BasicGameResources.stringBuffer.delete(0, BasicGameResources.stringBuffer.length());
        BasicGameResources.stringBuffer.append(ROOT);
        BasicGameResources.stringBuffer.append(UnitGameResources.ICON_BUILD);
        BasicGameResources.stringBuffer.append(string);
        BasicGameResources.stringBuffer.append(this.ICON_SIZE);

        this.RESOURCE_ICON_BUILD = BasicGameResources.stringBuffer.toString();

        /*
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(ROOT);
        stringBuffer.append(BASE);
        stringBuffer.append(string);
        stringBuffer.append(sizeString);

        this.RESOURCE_BASE = stringBuffer.toString();
        
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(ROOT);
        stringBuffer.append(EMPTY);
        stringBuffer.append(string);
        stringBuffer.append(sizeString);

        this.RESOURCE_EMPTY = stringBuffer.toString();
        */
    }
    
    @Override
    protected String getString() throws Exception
    {
        if (AndroidUtil.isAndroid())
        {
            return GameGraphicsResourceUtil.getInstance().getStringForFeature(
                    GraphicsFeatureFactory.getInstance().SPRITE_QUARTER_ROTATION_GRAPHICS);
        } else
        {
            return super.getString();
        }
    }

}
