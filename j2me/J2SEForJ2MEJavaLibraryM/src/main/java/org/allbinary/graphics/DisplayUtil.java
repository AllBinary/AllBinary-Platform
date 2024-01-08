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

import java.awt.Dimension;
import java.awt.Toolkit;

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
    
    public int width;
    public int height;
    public int width2;
    public int height2;
    
    private DisplayUtil() {

        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//
//        this.width = dimension.width;
//        this.height = dimension.height;
        this.width = 640;
        this.height = 480;
//        this.width = 1280;
//        this.height = 960;
//        this.width2 = this.width;
//        this.height2 = this.height;
        this.width2 = dimension.width;
        this.height2 = dimension.height;

    }

}
