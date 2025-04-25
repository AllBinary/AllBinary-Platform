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
public class ShaderStrings {
    
    private static final ShaderStrings instance = new ShaderStrings();

    /**
     * @return the instance
     */
    public static ShaderStrings getInstance() {
        return instance;
    }
    
    public final String NO_PROGRAM_HANDLE = "No program handle";
    
    public final String UNIFORM_BLOCK_HANDLE = "Uniform Block Handle: ";
    public final String UNIFORM_HANDLE = "Uniform Handle: ";
    public final String UNIFORM_ATTRIBUTE_HANDLE = "Uniform Attribute Handle: ";
    
}
