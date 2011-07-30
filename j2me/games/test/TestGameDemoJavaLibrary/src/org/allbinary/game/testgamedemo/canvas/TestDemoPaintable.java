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
package org.allbinary.game.testgamedemo.canvas;

import allbinary.game.paint.GameScrollMenuPaintable;
import allbinary.game.paint.MainGameDemoStatePaintable;
import allbinary.game.paint.OwnershipPaintable;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.paint.Paintable;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class TestDemoPaintable extends
GameScrollMenuPaintable
//BasicGameDemoPaintable
{

    public TestDemoPaintable(Paintable paintable)
        throws Exception
    {
        super(new MainGameDemoStatePaintable(OwnershipPaintable.getInstance(),
                paintable
        // new PressStartMenuPaintable()
                ),
                OwnershipPaintable.getInstance(),
                TestGameDemoHelpPaintable.getInstance(),
                BasicColorFactory.getInstance().YELLOW);
    }
}
