/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.input.media.image.capture;

import java.awt.image.BufferedImage;

import org.allbinary.logic.basic.util.event.AllBinaryEventObject;

public class CapturedImageWorkerResultsEvent extends AllBinaryEventObject
{
    private final Long frame;
    private BufferedImage bufferedImage;
    
    public CapturedImageWorkerResultsEvent(Object object, Long frame,
					   BufferedImage bufferedImage) {
	super(object);
	this.frame = frame;
	setBufferedImage(bufferedImage);
    }
    
    public BufferedImage getBufferedImage() {
	return bufferedImage;
    }
    
    private void setBufferedImage(BufferedImage bufferedImage) {
	this.bufferedImage = bufferedImage;
    }
    
    public Long getFrame() {
	return frame;
    }
}
