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
package org.allbinary.game.canvas;

import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.graphics.displayable.MyCanvas;

/**
 *
 * @author User
 */
public class ABToGBUtil {
    
    private static final ABToGBUtil instance = new ABToGBUtil();
    
    /**
     * @return the instance
     */
    public static ABToGBUtil getInstance() {
        return instance;
    }

    public MyCanvas abCanvas;
    public AllBinaryGameLayerManager allBinaryGameLayerManager;

}
