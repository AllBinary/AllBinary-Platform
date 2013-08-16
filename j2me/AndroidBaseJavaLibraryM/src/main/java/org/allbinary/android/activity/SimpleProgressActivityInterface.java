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
package org.allbinary.android.activity;

public interface SimpleProgressActivityInterface
{
    void runOnUiThread(Runnable action);
    
    void onSetProgress(int value, String text) throws Exception;
    void onDismissProgress(int[] animationSequence) throws Exception;
    void onShowProgress(boolean indeterminate, int[] animationSequence) throws Exception;
}
