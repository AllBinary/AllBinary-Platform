/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
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
package org.allbinary.game.midlet;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import org.allbinary.game.displayable.canvas.GameCanvasRunnableInterface;

/**
 *
 * @author User
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public interface DemoGameMidletJs {
    
    public GameCanvasRunnableInterface createDemoGameCanvasRunnableInterface()
            throws Exception;

}
