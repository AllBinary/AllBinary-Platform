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
package allbinary.game.ai;

import abcs.logic.basic.NotImplemented;
import abcs.logic.basic.string.CommonSeps;
import allbinary.ai.ArtificialIntelligence;
import allbinary.game.displayable.canvas.CanvasUtil;
import allbinary.game.input.GameInput;
import allbinary.game.input.GameKeyEventSourceInterface;
import allbinary.game.input.event.GameKeyEventFactory;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.logic.math.SmallIntegerSingletonFactory;

public class BasicAI 
extends ArtificialIntelligence
implements GameKeyEventSourceInterface
{
    public static final Integer AI_VISITOR = SmallIntegerSingletonFactory.getInstance().getInstance(2);
    
    public static final Integer ID = SmallIntegerSingletonFactory.getInstance().getInstance(1);

    private final AllBinaryLayer ownerLayerInterface;
    private final GameInput gameInput;

    private int lastKey = -1;

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    /*
    private BasicAI()
    {
       this.ownerLayerInterface = null;
       this.gameInput = null;
    }
    */
    
    public BasicAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput)
    {
        this.ownerLayerInterface = ownerLayerInterface;
        this.gameInput = gameInput;
    }

    public void processAI(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    public void processAI(int key) throws Exception
    {
        if (key != -1)
        {
            this.gameInput.add(gameKeyEventFactory.getInstance(this, key));
        }
    }

    public void setLastKey(int lastKey)
    {
        this.lastKey = lastKey;
    }

    public int getLastKey()
    {
        return lastKey;
    }

    public AllBinaryLayer getOwnerLayerInterface()
    {
        return ownerLayerInterface;
    }

    /*
    protected void setOwnerLayerInterface(AllBinaryLayer ownerLayerInterface)
    {
        this.ownerLayerInterface = ownerLayerInterface;
    }

    public void setGameInput(GameInput gameInput)
    {
        this.gameInput = gameInput;
    }
    */    
    
    public GameInput getGameInput()
    {
        return gameInput;
    }
 
    public String getName()
    {
        return this.getClass().getName();
    }
    
    public String toString()
    {
        StringBuilder stringBuffer = new StringBuilder();
        
        stringBuffer.append(CommonSeps.getInstance().NEW_LINE);
        stringBuffer.append(this.getName());
        stringBuffer.append(" LastKey: ");
        stringBuffer.append(CanvasUtil.getKeyName(this.getLastKey()));

        return stringBuffer.toString();
    }
    
    public int getSourceId()
    {
        return 1;
    }
}