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
package org.allbinary.data.resource;

import android.content.Context;
import org.allbinary.android.AndroidInfoFactory;

/**
 *
 * @author User
 */
public class NullAndroidContextFactory {
    
    public static Context getInstance() {
        
        if(AndroidInfoFactory.getInstance().getVersion() < 20) {
            return NullAndroidContextUpToAPI29.getInstance();
        } else {
            return NullAndroidContextAPI30AndUp.getInstance();
        }
    }
}
