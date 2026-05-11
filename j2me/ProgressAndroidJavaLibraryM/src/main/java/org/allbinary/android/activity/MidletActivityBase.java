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
package org.allbinary.android.activity;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import org.allbinary.android.GenericMotionEventVerifyInterface;
import org.allbinary.game.configuration.event.GameFeatureEvent;
import org.allbinary.game.configuration.event.GameFeatureListenerInterface;
import org.allbinary.game.configuration.event.GameInitializedEvent;
import org.allbinary.game.configuration.event.GameInitializedListenerInterface;
import org.allbinary.logic.util.event.AllBinaryEventObject;

/**
 *
 * @author User
 */
public class MidletActivityBase extends Activity
        implements GameFeatureListenerInterface,
        GameInitializedListenerInterface,
        SimpleProgressActivityInterface,
        KeyInputViewCompositeInterface,
        GenericMotionEventVerifyInterface
{
    @Override
    public void onGameFeatureChange(GameFeatureEvent gameFeatureEvent) {
        
    }

    @Override
    public void onEvent(AllBinaryEventObject eventObject) {
        
    }
    
    @Override
    public void onGameInitialized(GameInitializedEvent gameInitializedEvent) {
        
    }
        
    @Override
    public void onSetProgress(int value, String text) throws Exception {
        
    }
    
    @Override
    public void onDismissProgress(int[] animationSequence) throws Exception {
        
    }
    
    @Override
    public void onShowProgress(boolean indeterminate, int[] animationSequence) throws Exception {
        
    }
  
    @Override
    public View getRootView() {
        throw new RuntimeException();
    }
    
    @Override
    public View getView() {
        throw new RuntimeException();
    }
    
    @Override
    public WindowManager getWindowManager() {
        throw new RuntimeException();
    }

    @Override
    public void setKeyInputView(View keyInputView) {
        
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        throw new RuntimeException();
    }
    
}
