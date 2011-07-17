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
package org.allbinary.game.testgamedemo.resource;

import org.allbinary.game.testgamedemo.init.TestGameDemoResourceInitialization;

/*
 *  Note that errors may occur even though your importing R correctly
 *  This usually is caused by Duplicate R classes
 *  Solution is usually to move the R class from the org.allbinary.game.game_name 
 *  package into the independent src directory not part of the multi process apk
 *  Remark out for independent Activities
 */


public class TestGameDemoJ2MEResourceInitialization
extends TestGameDemoResourceInitialization
{
    public void init() 
    throws Exception
    {
        super.init();
        
      //TestGameDemoResources.init();
    }
}
