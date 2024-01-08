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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

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
    
    //This should come from javascript browser
    public int width = 1280;
    public int height = 960;
    public int width2 = 1280;
    public int height2 = 960;
    
    private DisplayUtil() {

    }

}
