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
package org.allbinary.graphics;

/**
 *
 * @author User
 */
public class DisplayUtil {
    
    private static final DisplayUtil instance = new DisplayUtil();

    /**
     * @return the instance
     */
    public static DisplayUtil getInstance() {
        return instance;
    }
    
    public int width = 640;
    public int height = 480;
    public int width2 = 640;
    public int height2 = 480;
    
    private DisplayUtil() {

    }

}
