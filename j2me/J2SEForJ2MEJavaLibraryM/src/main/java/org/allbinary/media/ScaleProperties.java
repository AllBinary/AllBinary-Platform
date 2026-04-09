/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.media;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;

/**
 *
 * @author User
 */
public class ScaleProperties {
    
    public static final ScaleProperties instance = new ScaleProperties();

    public float scaleX;
    public float scaleY;
   
    public int scaleWidth;
    public int scaleHeight;    
    
    public boolean shouldScale;
    
    public String toString() {
        return new StringMaker().append(CommonLabels.getInstance().COLON_SEP).appendboolean(this.shouldScale)
            .append("scaleX: ").appendfloat(this.scaleX)
            .append("scaleY: ").appendfloat(this.scaleY)
            .append("scaleWidth: ").appendint(this.scaleWidth)
            .append("scaleHeight: ").appendint(this.scaleHeight)
            .toString();
    }

}
