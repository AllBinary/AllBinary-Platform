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
package org.allbinary.android;

import android.os.Parcelable;
import android.os.Parcel;

/**
 *
 * @author User
 */
public class NullParcelable implements Parcelable {
    
    public static final NullParcelable NULL_PARCELABLE = new NullParcelable();
    
    @Override
    public int describeContents() {
        throw new RuntimeException();
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
    }

}
