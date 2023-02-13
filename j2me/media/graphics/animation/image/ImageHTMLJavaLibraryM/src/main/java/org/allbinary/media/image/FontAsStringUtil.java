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
package org.allbinary.media.image;

/**
 *
 * @author User
 */
public class FontAsStringUtil {
    
    private static final FontAsStringUtil instance = new FontAsStringUtil();

    /**
     * @return the instance
     */
    public static FontAsStringUtil getInstance() {
        return instance;
    }
    
    public final String[] FONT_SIZE_AS_STRING = new String[48];
    
    public FontAsStringUtil() {
        final int size = this.FONT_SIZE_AS_STRING.length;
        for(int index = 0; index < size; index++) {
            FONT_SIZE_AS_STRING[index] = Integer.toString(index);
        }
    }
}
