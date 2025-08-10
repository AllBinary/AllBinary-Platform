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
package org.allbinary.view;

import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author User
 */
public class NullEmulatorView implements EmulatorViewInterface {
    
    public static final NullEmulatorView NULL_EMULATOR_VIEW = new NullEmulatorView();
    
    @Override
    public void setMidlet(final MIDlet midlet) {
        
    }
    
    //KeyInputViewCompositeInterface
    @Override
    public void onEmulatorInitComplete(final Object midletActivity) {
        
    }

    @Override
    public void onSetDisplayable(Displayable displayable) {
        
    }
    
}
