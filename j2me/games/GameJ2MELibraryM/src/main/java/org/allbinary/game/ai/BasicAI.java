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
package org.allbinary.game.ai;

import org.allbinary.string.CommonStrings;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.ai.ArtificialIntelligence;
import org.allbinary.game.displayable.canvas.CanvasUtil;
import org.allbinary.game.input.GameInput;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class BasicAI 
extends ArtificialIntelligence
implements GameKeyEventSourceInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    public static final Integer AI_VISITOR = SmallIntegerSingletonFactory.getInstance().getInstance(2);
    
    public static final Integer ID = SmallIntegerSingletonFactory.getInstance().getInstance(1);

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
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
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
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
        StringMaker stringBuffer = new StringMaker();
        
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