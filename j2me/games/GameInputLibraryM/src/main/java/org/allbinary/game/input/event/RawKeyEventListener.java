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
package org.allbinary.game.input.event;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;

/**
 *
 * @author User
 */

@JsType
public interface RawKeyEventListener {
    
    @JsMethod
    void onEventRaw(final int keyCode, final int deviceId, final boolean repeated);

}
