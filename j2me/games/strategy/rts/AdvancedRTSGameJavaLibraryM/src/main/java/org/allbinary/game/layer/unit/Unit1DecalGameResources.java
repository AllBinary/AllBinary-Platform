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

public class Unit1DecalGameResources extends BasicGameResources
{
    private static final Unit1DecalGameResources instance = 
        new Unit1DecalGameResources();
    
    private Unit1DecalGameResources()
    {
        final String ROOT = "/unit_decal";
        final String SMALL = "_20_by_20.png";
        final String MEDIUM = SMALL;
        final String SIZE_FOUR = SMALL;
        final String SIZE_FIVE = SMALL;
        final String SIZE_SIX = SMALL;
        
        final String[] SIZE = { SMALL, MEDIUM, SIZE_FOUR, SIZE_FIVE, SIZE_SIX };
        
        super.init(ROOT, SIZE);
    }

    @Override
    protected void init(String ROOT, String sizeString) throws Exception
    {
        super.init(ROOT, sizeString);
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

    public static BasicGameResources getInstance()
    {
        return instance;
    }
}
