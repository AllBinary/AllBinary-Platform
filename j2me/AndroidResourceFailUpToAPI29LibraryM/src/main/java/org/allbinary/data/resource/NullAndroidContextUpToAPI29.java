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

/**
 *
 * @author User
 */
public class NullAndroidContextUpToAPI29 {
 
    public static final Context getInstance() {
        throw new RuntimeException();
    }
    
}
