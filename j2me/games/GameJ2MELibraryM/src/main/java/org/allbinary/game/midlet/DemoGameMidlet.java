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
package org.allbinary.game.midlet;

import jsinterop.annotations.JsType;

import java.util.Hashtable;

import org.allbinary.game.displayable.canvas.GameCanvasRunnableInterface;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.system.security.licensing.ClientInformationFactory;
import org.allbinary.thread.PrimaryThreadPool;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import org.allbinary.game.displayable.canvas.NullGameCanvasRunnable;


@JsType
public class DemoGameMidlet extends GameMidlet
{

    @JsConstructor
    public DemoGameMidlet(final ClientInformationFactory clientInformationFactory)
    {
        super(clientInformationFactory);
    }
    
    @JsMethod
    protected boolean isContinue() throws Exception
    {
        Hashtable hashtable = this.getStartStateHashtable();
        if (hashtable != null && hashtable.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @JsMethod
    public int getHighestLevel()
    {
        return 1;
    }
    
    @JsMethod
    public GameCanvasRunnableInterface createDemoGameCanvasRunnableInterface()
            throws Exception
    {
        this.logUtil.putF(this.commonStrings.NOT_IMPLEMENTED, this, this.commonStrings.CREATE);
        return NullGameCanvasRunnable.NULL_GAME_CANVAS_RUNNABLE;
        //throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }

    @JsMethod
    protected GameCanvasRunnableInterface createGameCanvasRunnable(
            AllBinaryGameLayerManager allBinaryGameLayerManager)
            throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }
    
    @JsMethod
    protected GameCanvasRunnableInterface createGameCanvasRunnableInterface()
            throws Exception
    {
        return this.createGameCanvasRunnable(this.createGameLayerManager());
    }
    
    /*
     * protected void mediaInit() throws Exception { throw new Exception("Not
     * Implemented"); }
     */

    @JsMethod
    public void demoSetup()
    {
    }

    
    @JsMethod
    public void postDemoSetup()
    {
        
    }
 
    @Override
    @JsMethod
    public synchronized void setDemo() throws Exception
    {
        this.logUtil.putF(this.commonStrings.START, this, "setDemo");

        ////TWB - Loading Feature Change - Can remove remark after testing
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        progressCanvas.start();

        PrimaryThreadPool.getInstance().runTask(new DemoRunnable(this));
        //this.postDemoSetup();
    }

    @Override
    @JsMethod
    public synchronized void createGame() throws Exception
    {
        final String CREATE_GAME = "createGame";
        this.logUtil.putF(this.commonStrings.START, this, CREATE_GAME);

        ////TWB - Loading Feature Change - Can remove remark after testing
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        progressCanvas.start();
        
        Hashtable hashtable = this.getStartStateHashtable();
        this.setStartStateHashtable(NullUtil.getInstance().NULL_TABLE);

        PrimaryThreadPool.getInstance().runTask(new CreateGameRunnable(this, hashtable));

        this.logUtil.putF(this.commonStrings.END, this, CREATE_GAME);
    }

    @JsMethod
    public boolean isReady()
    {
       return true;
    }
}
