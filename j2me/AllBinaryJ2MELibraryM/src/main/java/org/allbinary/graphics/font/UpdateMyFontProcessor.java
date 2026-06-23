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

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author User
 */
public class UpdateMyFontProcessor extends MyFontProcessor {

    private final UpdateMyFontInterface updateMeasurementProcessor;

    public UpdateMyFontProcessor(final UpdateMyFontInterface keyValueDrawCharArray) {
        this.updateMeasurementProcessor = keyValueDrawCharArray;
    }

    @Override
    public void process(final Graphics graphics) {
        this.updateMeasurementProcessor.updateMeasurement(graphics);
    }

}
