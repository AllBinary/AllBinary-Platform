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

public class UnitGameResources extends BasicGameResources
{
    public String NAME;
    //public String RESOURCE_EMPTY;
    //public String RESOURCE_BASE;

    public String RESOURCE_ICON_ANIMATION;
    public String RESOURCE_ICON_BUILD;
    public String RESOURCE_ICON;

    protected UnitGameResources()
    {
    }

    private final static String ICON = "_icon";
    private final static String BUILD = "_build";
    private final static String DESTROY = "_destroy";
    private final static String ICON_BUILD = "_icon" + BUILD;
    //private final static String BASE = "_base";
    //private final static String EMPTY = "_empty";

    private final String ICON_SIZE = "_64_by_64.png";
    
    @Override
    protected void init(String ROOT, String sizeString) throws Exception
    {
        super.init(ROOT, sizeString);

        String string = GameGraphicsResourceUtil.getInstance().getName();

        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(ROOT);
        stringBuffer.append(DESTROY);
        stringBuffer.append(string);
        stringBuffer.append(sizeString);

        this.RESOURCE_DESTROY = stringBuffer.toString();
        
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(ICON);
        stringBuffer.append(ROOT);

        this.RESOURCE_ICON_ANIMATION = stringBuffer.toString();

        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(ROOT);
        stringBuffer.append(ICON);
        stringBuffer.append(string);
        stringBuffer.append(ICON_SIZE);

        this.RESOURCE_ICON = stringBuffer.toString();

        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(ROOT);
        stringBuffer.append(ICON_BUILD);
        stringBuffer.append(string);
        stringBuffer.append(ICON_SIZE);

        this.RESOURCE_ICON_BUILD = stringBuffer.toString();

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
            return GameGraphicsResourceUtil.getInstance().getString(
                    GraphicsFeatureFactory.getInstance().SPRITE_QUARTER_ROTATION_GRAPHICS);
        } else
        {
            return super.getString();
        }
    }

}
