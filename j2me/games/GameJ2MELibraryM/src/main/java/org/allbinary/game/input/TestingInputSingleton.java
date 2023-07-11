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
package org.allbinary.game.input;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.layer.AllBinaryGameLayer;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.layer.AllBinaryLayerManager;

public class TestingInputSingleton 
    extends AllBinaryGameLayer
    implements GameInputInterface
{
    private static final TestingInputSingleton instance = 
        new TestingInputSingleton();
    
    public static TestingInputSingleton getInstance()
    {
        return instance;
    }
    
    protected final GameInputProcessor[] inputProcessorArray = 
        new GameInputProcessor[InputFactory.getInstance().MAX];
    
    private TestingInputSingleton()
    {
        super(RectangleFactory.SINGLETON);
        
        GameInputProcessorUtil.init(this.inputProcessorArray);
    }
    
    private PaintableInterface paintable = NullPaintable.getInstance();
    
    public void initInputProcessors()
    {
        
    }
    
    public void initInputProcessors(TestInputInterface aTestInputInterface)
    {
        final TestInputInterface testInputInterface = aTestInputInterface;
        this.paintable = testInputInterface;
        
        this.inputProcessorArray[Canvas.UP] = new GameInputProcessor()
        {
            public void process(AllBinaryLayerManager allbinaryLayerManager, GameKeyEvent gameKeyEvent)
            throws Exception
            {
                testInputInterface.up();
            }
        };

        this.inputProcessorArray[Canvas.DOWN] = new GameInputProcessor()
        {
            public void process(AllBinaryLayerManager allbinaryLayerManager, GameKeyEvent gameKeyEvent)
            throws Exception
            {
                testInputInterface.down();
            }
        };

        this.inputProcessorArray[Canvas.LEFT] = new GameInputProcessor()
        {
            public void process(AllBinaryLayerManager allbinaryLayerManager, GameKeyEvent gameKeyEvent)
            throws Exception
            {
                testInputInterface.left();
            }
        };

        this.inputProcessorArray[Canvas.RIGHT] = new GameInputProcessor()
        {
            public void process(AllBinaryLayerManager allbinaryLayerManager, GameKeyEvent gameKeyEvent)
            throws Exception
            {
                testInputInterface.right();
            }
        };

        this.inputProcessorArray[Canvas.KEY_NUM7] = new GameInputProcessor()
        {
            public void process(AllBinaryLayerManager allbinaryLayerManager, GameKeyEvent gameKeyEvent)
            throws Exception
            {
                testInputInterface.strafeLeft();
            }
        };

        this.inputProcessorArray[Canvas.KEY_NUM9] = new GameInputProcessor()
        {
            public void process(AllBinaryLayerManager allbinaryLayerManager, GameKeyEvent gameKeyEvent)
            throws Exception
            {
                testInputInterface.strafeRight();
            }
        };
        
        GameInputProcessorUtil.init(this.inputProcessorArray);
    }

    public synchronized void processInput(
            AllBinaryLayerManager allbinaryLayerManager, 
            BasicArrayList list)
        throws Exception
    {
        int size = list.size();

        int key = 0;

        for (int index = 0; index < size; index++)
        {
            key = ((GameKeyEvent) list.objectArray[index]).getKey();

            inputProcessorArray[key].process(allbinaryLayerManager, (GameKeyEvent) null);
        }
    }
    
    public synchronized void processInput(AllBinaryLayerManager allbinaryLayerManager)
    throws Exception
    {
        this.processInput(allbinaryLayerManager, this.getGameKeyEventList());
        this.getGameKeyEventList().clear();
    }
    
    public void paint(Graphics graphics)
    {
        this.paintable.paint(graphics);
    }
    
    public boolean implmentsGameInputInterface()
    {
        return true;
    }    
}
