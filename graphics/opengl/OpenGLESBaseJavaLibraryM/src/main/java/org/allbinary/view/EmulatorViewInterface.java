package org.allbinary.view;

import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public interface EmulatorViewInterface
{
    void setMidlet(final MIDlet midlet);
    
    //KeyInputViewCompositeInterface
    void onEmulatorInitComplete(final Object midletActivity);

    void onSetDiplayable(Displayable displayable);
}