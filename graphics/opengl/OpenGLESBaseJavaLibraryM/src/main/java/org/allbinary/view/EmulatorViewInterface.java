package org.allbinary.view;

import javax.microedition.lcdui.Displayable;

public interface EmulatorViewInterface
{
    //KeyInputViewCompositeInterface
    void onEmulatorInitComplete(final Object midletActivity);

    void onSetDiplayable(Displayable displayable);
}