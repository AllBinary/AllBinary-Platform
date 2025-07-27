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
package org.allbinary.layer;

import org.allbinary.graphics.Rectangle;
import org.allbinary.view.ViewPosition;

public class AllBinaryLayerFactory 
implements AllBinaryLayerFactoryInterface
{
    private final Rectangle rectangle;

    public AllBinaryLayerFactory(Rectangle rectangle)
    {
        this.rectangle = rectangle;
    }

    @Override
    public AllBinaryLayer getInstance()
    {
        return new AllBinaryLayer(rectangle, new ViewPosition());
    }
}
