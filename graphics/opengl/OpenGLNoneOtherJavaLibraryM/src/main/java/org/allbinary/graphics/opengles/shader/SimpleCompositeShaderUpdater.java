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
package org.allbinary.graphics.opengles.shader;

/**
 *
 * @author User
 */
public class SimpleCompositeShaderUpdater extends CompositeShaderUpdater {
    
    public SimpleCompositeShaderUpdater(final String[] uniformBlockArray, final String[] uniformArray, final String[] attributeArray) {
        super(uniformBlockArray, uniformArray, attributeArray);
    }

    public SimpleCompositeShaderUpdater(final String[] uniformBlockArray, final String[] uniformArray, final String[] attributeArray, final int[] attributeHandleArray) {
        super(uniformBlockArray, uniformArray, attributeArray, attributeHandleArray);
    }
        
}
