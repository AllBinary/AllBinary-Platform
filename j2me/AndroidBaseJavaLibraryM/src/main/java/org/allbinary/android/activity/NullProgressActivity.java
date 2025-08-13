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

/**
 *
 * @author User
 */
public class NullProgressActivity implements ProgressActivityInterface {
 
    public static final NullProgressActivity NULL_PROGRESS_ACTIVITY = new NullProgressActivity();
    
    @Override
    public void runOnUiThread(Runnable action) {
        
    }
    
    @Override
    public void onDismissTitleProgressBar() {
        
    }
    
    @Override
    public void onShowTitleProgressBar(int value, boolean indeterminate) {
        
    }
    
    @Override
    public void onTitleProgressBarSetProgress(int value) {
        
    }
    
}
