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
public class ImageStrings {

    private static final ImageStrings instance = new ImageStrings();

    /**
     * @return the instance
     */
    public static ImageStrings getInstance() {
        return instance;
    }
    
    public final String PNG = "PNG";
    public final String PNG_EXTENSION = ".png";
}
