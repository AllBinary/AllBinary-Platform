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

import android.view.View;

/**
 *
 * @author User
 */
public class NullKeyInputViewComposite implements KeyInputViewCompositeInterface {
    
    public static final NullKeyInputViewComposite NULL_KEY_INPUT_VIEW_COMPOSITE = new NullKeyInputViewComposite();
   
    @Override
    public void setKeyInputView(View keyInputView) {
        
    }

    @Override
    public View getRootView() {
        throw new RuntimeException();
    }

    @Override
    public View getView() {
        throw new RuntimeException();
    }
    
//    @Override
//    public WindowManager getWindowManager() {
//        throw new RuntimeException();
//    }
    
    @Override
    public void runOnUiThread(Runnable action) {
        
    }
    
}
