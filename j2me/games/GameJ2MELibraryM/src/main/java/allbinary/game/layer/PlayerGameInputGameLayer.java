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
package allbinary.game.layer;

import javax.microedition.lcdui.Graphics;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.PlayerGameInput;
import allbinary.game.input.PlayerGameInputCompositeInterface;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.view.StaticViewPosition;

public class PlayerGameInputGameLayer extends AllBinaryGameLayer
    implements PlayerGameInputCompositeInterface
{
    private PlayerGameInput playerGameInput;
   
    public PlayerGameInputGameLayer(int playerInputId)
        throws Exception
    {
        super(
            new Rectangle(PointFactory.getInstance().ZERO_ZERO, 0, 0),
            new StaticViewPosition(0, 0, 0));

        this.playerGameInput = new PlayerGameInput(this.getGameKeyEventList(), playerInputId);
        
        LogUtil.put(LogFactory.getInstance("Danger Danger Danger: Should Not Be Called Except For Testing Input", this, "paint"));
    }
    
    public PlayerGameInput getPlayerGameInput()
    {
        return this.playerGameInput;
    }

    public void initInputProcessors()
    {
    }
    
    public void processInput(AllBinaryLayerManager myManager) throws Exception
    {
        
    }
    
   public void paint(Graphics graphics)
   {
       //LogUtil.put(LogFactory.getInstance("Should Not Be Called Except For Testing Input", this, "paint"));
	   super.paint(graphics);
   }
}
