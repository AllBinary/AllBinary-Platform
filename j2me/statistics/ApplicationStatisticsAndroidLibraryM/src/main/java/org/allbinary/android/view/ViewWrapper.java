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
package org.allbinary.android.view;

import android.view.View;

/**
 *
 * @author User
 */
public class ViewWrapper extends BaseViewWrapper {
    
    private final View view;
    
    public ViewWrapper(final View view) {
        this.view = view;
    }
    
    @Override
    public void invalidate() {
        this.view.invalidate();
    }
    
    @Override
    public void postInvalidate() {
        this.view.postInvalidate();
    }
    
}
