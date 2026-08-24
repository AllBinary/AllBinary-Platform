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
package org.allbinary.graphics.font;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

/**
 *
 * @author User
 */

@JsType
public class UpdateMyFontProcessor extends MyFontProcessor {

    private final UpdateMyFontInterface updateMeasurementProcessor;

    @JsConstructor
    public UpdateMyFontProcessor(final UpdateMyFontInterface keyValueDrawCharArray) {
        this.updateMeasurementProcessor = keyValueDrawCharArray;
    }

    @Override
    @JsMethod
    public void process(final Graphics graphics) {
        this.updateMeasurementProcessor.updateMeasurement(graphics);
    }

}
