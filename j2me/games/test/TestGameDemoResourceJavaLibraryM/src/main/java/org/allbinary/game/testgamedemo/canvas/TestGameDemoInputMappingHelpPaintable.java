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

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.testgamedemo.input.TestGameDemoGameInputMappingFactory;

import allbinary.animation.AnimationInterface;
import allbinary.game.paint.help.HelpPaintable;
import allbinary.game.paint.help.InputMappingHelpPaintable;
import allbinary.graphics.color.BasicColorFactory;

public class TestGameDemoInputMappingHelpPaintable 
    extends InputMappingHelpPaintable
    implements AnimationInterface
{    
    private static HelpPaintable SINGLETON = new TestGameDemoInputMappingHelpPaintable();
    
    public static HelpPaintable getInstance()
    {
        return SINGLETON;
    }
    
    private TestGameDemoInputMappingHelpPaintable()
    {
        super(TestGameDemoGameInputMappingFactory.getInstance().get(), 
                BasicColorFactory.getInstance().BLACK, 
                BasicColorFactory.getInstance().YELLOW);
    }

    //Remove hack after resource factory supports Paintable
    public void paint(Graphics graphics, int x, int y)
    {
        
    }

    public void paintThreed(Graphics graphics, int x, int y, int z)
    {
    }
    
    public void nextFrame() throws Exception
    {
        
    }
}
