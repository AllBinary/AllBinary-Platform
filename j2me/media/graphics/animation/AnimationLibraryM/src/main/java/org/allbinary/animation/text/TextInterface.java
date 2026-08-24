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
package org.allbinary.animation.text;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author User
 */

@JsType
public interface TextInterface {
 
    @JsMethod
    void setTextWithOnMeasure(final String text, final TextChangeListener textChangeListener);
    @JsMethod
    String getText();
    @JsMethod
    void setText(final String text);
    @JsMethod
    int getFontHeight();
    @JsMethod
    int getWidth();

}
