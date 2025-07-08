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

import java.util.Hashtable;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.displayable.canvas.GameCanvasRunnableInterface;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.logic.system.security.licensing.ClientInformationFactory;
import org.allbinary.thread.PrimaryThreadPool;

public class DemoGameMidlet extends GameMidlet
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public DemoGameMidlet(final ClientInformationFactory clientInformationFactory)
    {
        super(clientInformationFactory);
    }
    
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

    public int getHighestLevel()
    {
        return 1;
    }
    
    public GameCanvasRunnableInterface createDemoGameCanvasRunnableInterface()
            throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    protected GameCanvasRunnableInterface createGameCanvasRunnableInterface(
            AllBinaryGameLayerManager allBinaryGameLayerManager)
            throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }
    
    protected GameCanvasRunnableInterface createGameCanvasRunnableInterface()
            throws Exception
    {
        return this.createGameCanvasRunnableInterface(this.createGameLayerManager());
    }
    
    /*
     * protected void mediaInit() throws Exception { throw new Exception("Not
     * Implemented"); }
     */

    public void demoSetup()
    {
    }

    
    public void postDemoSetup()
    {
        
    }
    
    public synchronized void setDemo() throws Exception
    {
        logUtil.put(commonStrings.START, this, "setDemo");

        ////TWB - Loading Feature Change - Can remove remark after testing
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        progressCanvas.start();

        PrimaryThreadPool.getInstance().runTask(new DemoRunnable(this));
        //this.postDemoSetup();
    }

    public synchronized void createGame() throws Exception
    {
        final String CREATE_GAME = "createGame";
        logUtil.put(commonStrings.START, this, CREATE_GAME);

        ////TWB - Loading Feature Change - Can remove remark after testing
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        progressCanvas.start();
        
        Hashtable hashtable = this.getStartStateHashtable();
        this.setStartStateHashtable(null);

        PrimaryThreadPool.getInstance().runTask(new CreateGameRunnable(this, hashtable));

        logUtil.put(commonStrings.END, this, CREATE_GAME);
    }

    public boolean isReady()
    {
       return true;
    }
}
