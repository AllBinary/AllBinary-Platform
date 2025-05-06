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
package org.allbinary.game.resource;

/**
 *
 * @author User
 */
public class GDLazyResources
{
    private static final GDLazyResources instance = new GDLazyResources();

    /**
     * @return the instance
     */
    public static GDLazyResources getInstance()
    {
        return instance;
    }

    public final int[] imageResourceWidthArray = {
    };
    
    public final int[] imageResourceHeightArray = {
    };
    
    public final String[] requiredResourcesBeforeLoadingArray = {
    };

}

