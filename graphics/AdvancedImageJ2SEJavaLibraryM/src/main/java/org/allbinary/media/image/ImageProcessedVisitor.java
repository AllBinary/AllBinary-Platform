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
package org.allbinary.media.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author User
 */
public interface ImageProcessedVisitor extends ImageProcessorInputCompositeInterface {
    
    public void visit(final BufferedImage generatedBufferedImage, final String nameEnding, final int index) throws IOException;
}
