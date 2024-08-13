package org.allbinary.view;

import org.allbinary.game.configuration.event.GameInitializedListenerInterface;
import org.allbinary.game.midlet.DemoGameMidletEventListener;
import org.allbinary.midlet.MidletCompositeInterface;

public interface AllBinaryMidletViewInterface
extends MidletCompositeInterface,
EmulatorViewInterface, 
DemoGameMidletEventListener, 
GameInitializedListenerInterface
{
    void onResume();
    void onPause();
    void onDestroy();
}
