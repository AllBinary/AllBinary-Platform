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
package org.allbinary.input.automation.module.game;

import java.awt.*;

import org.allbinary.input.automation.module.InputAutomationActionInterface;

public interface BasicGameInputAutomationActionInterface
    extends InputAutomationActionInterface
{
    void attack(Rectangle rectangle) throws Exception;

    void target(Rectangle rectangle) throws Exception;
}
