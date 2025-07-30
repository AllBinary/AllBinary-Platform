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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;

import org.allbinary.math.AngleInfo;

public class ImageArrayRotationAnimationInfo {

	private Image[] imageArray;
	private int angleIncrement;
	private int totalAngle; 
	private int dx; 
	private int dy;
	
	public ImageArrayRotationAnimationInfo(
			Image[] imageArray, int angleIncrement, int totalAngle, int dx, int dy)
	{
		this.imageArray = imageArray;
		this.angleIncrement = angleIncrement;
		this.totalAngle = totalAngle;
		this.dx = dx;
		this.dy = dy;
	}

	public void setImageArray(Image[] imageArray) {
		this.imageArray = imageArray;
	}

	public Image[] getImageArray() {
		return imageArray;
	}

	public AngleInfo getAngleInfoP() {
		return AngleInfo.getInstance((short) angleIncrement);
	}

	public void setTotalAngle(int totalAngle) {
		this.totalAngle = totalAngle;
	}

	public int getTotalAngle() {
		return totalAngle;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDx() {
		return dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getDy() {
		return dy;
	}
}
