/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

/**
 *
 * @author User
 */
public interface KeyInterface {
    
    void keyPressed(int keyCode);
    
    void keyReleased(int keyCode);
    
    void keyPressedByDevice(int keyCode, int deviceId);

    void keyReleasedByDevice(int keyCode, int deviceId);
    
}
