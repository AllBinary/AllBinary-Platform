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
package allbinary.layer;

import allbinary.graphics.Rectangle;
import allbinary.view.ViewPosition;

public class AllBinaryLayerFactory 
implements AllBinaryLayerFactoryInterface
{
    private final Rectangle rectangle;

    public AllBinaryLayerFactory(Rectangle rectangle)
    {
        this.rectangle = rectangle;
    }

    public AllBinaryLayer getInstance()
    {
        return new AllBinaryLayer(rectangle, new ViewPosition());
    }
}
