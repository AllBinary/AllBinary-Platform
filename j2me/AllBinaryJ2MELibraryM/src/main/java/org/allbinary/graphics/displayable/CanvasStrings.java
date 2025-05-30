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
package org.allbinary.graphics.displayable;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

/**
 *
 * @author User
 */
public class CanvasStrings {
    
    private static final CanvasStrings instance = new CanvasStrings();

    /**
     * @return the instance
     */
    public static CanvasStrings getInstance() {
        return instance;
    }
    
    public final String CONSTRUCTOR = "MyCanvas::MyCanvas";
    public final String PAUSE = "MyCanvas::pause";
    public final String UN_PAUSE = "MyCanvas::unPause";
    public final String SET_FULL_SCREEN_MODE = "setFullScreenMode";
    public final String SIZE_CHANGED = "sizeChanged";
    public final String SCALED_IMAGES = "scaledImages";
    
    public final String ON_DISPLAY_CHANGE_EVENT = "onDisplayChangeEvent";
    
    public final BasicArrayList EMPTY_CHILD_NAME_LIST = BasicArrayListUtil.getInstance().getImmutableInstance();
    
    public final String ABOUT = "About";
    public final String OPTIONS = "Options";
    
    public final String FONT_ATLAS = "/font.png";
    
    public final String OBJ_MODEL = "_obj";
    public final String _PNG = ".png";
}
