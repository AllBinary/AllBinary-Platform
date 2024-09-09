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
package javax.microedition.lcdui;

/**
 *
 * @author User
 */
public class PostLoadPlatformImage extends PlatformImage {

    private static final PostLoadPlatformImage instance = new PostLoadPlatformImage();

    /**
     * @return the instance
     */
    public static PostLoadPlatformImage getInstance() {
        return instance;
    }

}
