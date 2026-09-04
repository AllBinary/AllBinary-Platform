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

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;;

import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.ABHashtable;

public class ScreenScavangerRobot
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public ScreenScavangerRobot() throws Exception {
    }
    
    public BufferedImage[] getScreenAsBufferedImages() throws Exception {
	this.logUtil.putF(this.commonStrings.START, this, "getScreenAsBufferedImages");
	final Dimension dimScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	final Rectangle rectScreenSize = new Rectangle(dimScreenSize);
	final ABHashtable robotHashtable = InputRobotFactory.getInstance().get();
	BufferedImage[] bufferedImageArray = new BufferedImage[1];
	int index = 0;
	final InputRobotInterface inputRobotInterface
	    = (InputRobotInterface) robotHashtable.get("Java Robot");
	bufferedImageArray[index]
	    = inputRobotInterface.createScreenCapture(rectScreenSize);
	this.logUtil.putF("Finish", this, "getScreenAsBufferedImages");
	return bufferedImageArray;
    }
}
