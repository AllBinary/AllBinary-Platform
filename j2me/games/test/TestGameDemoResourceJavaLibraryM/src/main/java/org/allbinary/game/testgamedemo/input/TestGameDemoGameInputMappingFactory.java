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
package org.allbinary.game.testgamedemo.input;

import allbinary.game.input.GameKeyFactory;
import allbinary.game.input.mapping.GameInputMapping;

public class TestGameDemoGameInputMappingFactory
{
    private static final TestGameDemoGameInputMappingFactory SINGLETON = new TestGameDemoGameInputMappingFactory();
    
    public static TestGameDemoGameInputMappingFactory getInstance()
    {
        return SINGLETON;
    }
    
    private GameInputMapping[] gameInputMappingArray = new GameInputMapping[7];
    
    public TestGameDemoGameInputMappingFactory()
    {   
        final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
        
        //Enter, 1, or Y",
        gameInputMappingArray[0] = new GameInputMapping("Fire", gameKeyFactory.KEY_NUM1);
        
        //Forward= Up, 2, or U
        gameInputMappingArray[1] = new GameInputMapping("Up", gameKeyFactory.UP);
        
        //Turn= Left, 4, or H
        gameInputMappingArray[2] = new GameInputMapping("Left", gameKeyFactory.LEFT);
        
        //Turn= Right, 6, or K
        gameInputMappingArray[3] = new GameInputMapping("Right", gameKeyFactory.RIGHT);

        //Reverse= Down, 8, or M
        gameInputMappingArray[4] = new GameInputMapping("Down", gameKeyFactory.DOWN);
        
        //Test game
        gameInputMappingArray[5] = new GameInputMapping("Zoom Out", gameKeyFactory.KEY_NUM3);
        gameInputMappingArray[6] = new GameInputMapping("Zoom In", gameKeyFactory.KEY_NUM0);

        /*        
        //Forward= Up, 2, or U
        gameInputMappingArray[1] = new GameInputMapping("Forward", gameKeyFactory.UP);
        
        //Turn= Left, 4, or H
        gameInputMappingArray[2] = new GameInputMapping("Left Turn", gameKeyFactory.LEFT);
        
        //Turn= Right, 6, or K
        gameInputMappingArray[3] = new GameInputMapping("Right Turn", gameKeyFactory.RIGHT);

        //Reverse= Down, 8, or M
        gameInputMappingArray[4] = new GameInputMapping("Reverse", gameKeyFactory.DOWN);

        //Shield= 3, *, Space
        gameInputMappingArray[5] = new GameInputMapping("Shield", gameKeyFactory.KEY_NUM3);

        //Special 1= 0 or Insert
        gameInputMappingArray[6] = new GameInputMapping("Special 1", gameKeyFactory.KEY_NUM0);

        //Special 2= # or Delete
        gameInputMappingArray[7] = new GameInputMapping("Special 2", gameKeyFactory.KEY_POUND);

        gameInputMappingArray[8] = new GameInputMapping("All Stop", gameKeyFactory.KEY_NUM5);

        //Strafe Left= 7
        gameInputMappingArray[9] = new GameInputMapping("Strafe Left", gameKeyFactory.KEY_NUM7);

        //Strafe Right= 9
        gameInputMappingArray[10] = new GameInputMapping("Strafe Right", gameKeyFactory.KEY_NUM9);
        */

    }
    
    public GameInputMapping[] get()
    {
        return this.gameInputMappingArray;
    }
}
