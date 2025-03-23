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
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class ScreenScavangerRobot
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public ScreenScavangerRobot() throws Exception {
    }
    
    public BufferedImage[] getScreenAsBufferedImages() throws Exception {
	LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "getScreenAsBufferedImages"));
	Dimension dimScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Rectangle rectScreenSize = new Rectangle(dimScreenSize);
	Hashtable robotHashtable = InputRobotFactory.getInstance().get();
	BufferedImage[] bufferedImageArray = new BufferedImage[1];
	int index = 0;
	InputRobotInterface inputRobotInterface
	    = (InputRobotInterface) robotHashtable.get("Java Robot");
	bufferedImageArray[index]
	    = inputRobotInterface.createScreenCapture(rectScreenSize);
	LogUtil.put(LogFactory.getInstance("Finish", this, "getScreenAsBufferedImages"));
	return bufferedImageArray;
    }
}
