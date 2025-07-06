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
package org.allbinary.game.layer.geological.resources;

/**
 *
 * @author user
 */
public class GeologicalResourceInfoHudPaintableSingleton {

    private static final GeologicalResourceInfoHudPaintable instance =
        new GeologicalResourceInfoHudPaintable();

    public static GeologicalResourceInfoHudPaintable getInstance()
    {
        return instance;
    }

}
