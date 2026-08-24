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

import jsinterop.annotations.JsType;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class CanvasStrings {
    
    private static final CanvasStrings instance = new CanvasStrings();

    /**
     * @return the instance
     */
    @JsMethod
    public static CanvasStrings getInstance() {
        return CanvasStrings.instance;
    }
    
    @JsProperty
    public final String CONSTRUCTOR = "MyCanvas::MyCanvas";
    @JsProperty
    public final String PAUSE = "MyCanvas::pause";
    @JsProperty
    public final String UN_PAUSE = "MyCanvas::unPause";
    @JsProperty
    public final String SET_FULL_SCREEN_MODE = "setFullScreenMode";
    @JsProperty
    public final String SIZE_CHANGED = "sizeChanged";
    @JsProperty
    public final String SCALED_IMAGES = "scaledImages";
    
    @JsProperty
    public final String PAINT = "paint";
    @JsProperty
    public final String ON_DISPLAY_CHANGE_EVENT = "onDisplayChangeEvent";
    @JsProperty
    public final String UPDATE_MEASUREMENT = "updateMeasurement";
    @JsProperty
    public final String FD_WIDTH = " font/display width: ";
    @JsProperty
    public final String FD_HEIGHT = " font/display height: ";
    
    @JsProperty
    public final BasicArrayList EMPTY_CHILD_NAME_LIST = BasicArrayListUtil.getInstance().getImmutableInstance();
    
    @JsProperty
    public final String ABOUT = "About";
    @JsProperty
    public final String OPTIONS = "Options";
    
    @JsProperty
    public final String FONT_ATLAS = "/font.png";
    
    @JsProperty
    public final String OBJ_MODEL = "_obj";
    @JsProperty
    public final String MD2_MODEL = "_md2";
    @JsProperty
    public final String _PNG = ".png";
}
