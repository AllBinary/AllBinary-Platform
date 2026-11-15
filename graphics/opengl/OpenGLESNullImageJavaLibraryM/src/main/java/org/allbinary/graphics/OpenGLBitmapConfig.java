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
package org.allbinary.graphics;

/**
 * The bitmap config of OpenGLBitmapFactory.createBitmap. It was the nested class OpenGLBitmapFactory.Config, which is
 * its own class since most of the polyglot languages do not have nested classes.
 */
public class OpenGLBitmapConfig {

    public final Object bitmapConfig;

    public OpenGLBitmapConfig(final Object bitmapConfig) {
        this.bitmapConfig = bitmapConfig;
    }
}
