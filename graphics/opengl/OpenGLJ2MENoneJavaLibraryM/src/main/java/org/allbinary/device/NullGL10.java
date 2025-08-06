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
package org.allbinary.device;

import javax.microedition.khronos.opengles.GL10;

/**
 *
 * @author User
 */
public class NullGL10 implements GL10 {

    public static final NullGL10 NULL_GL10 = new NullGL10();

}
