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
package org.allbinary.animation.image;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;

/**
 *
 * @author User
 */
public class AnimationFactoryInitializationVisitor {
    
    public int width;
    public int height;
    public int dx;
    public int dy;

    public void visit() {
        
    }
    
    public String toString() {
        final CommonSeps commonSeps = CommonSeps.getInstance();
        final CommonLabels commonLabels = CommonLabels.getInstance();
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(commonLabels.WIDTH_LABEL);
        stringBuffer.append(width);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(commonLabels.HEIGHT_LABEL);
        stringBuffer.append(height);

        return stringBuffer.toString();
    }

}
