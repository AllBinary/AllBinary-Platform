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
package org.allbinary.game.physics.acceleration;

public class BasicAccelerationProperties {

	private int forward;
	private int reverse;
	
	public BasicAccelerationProperties(
			int forward, int reverse)
	{
		this.setForward(forward);
		this.setReverse(reverse);
	}
	
	public void setReverse(int reverse) {
		this.reverse = reverse;
	}
	public int getReverse() {
		return reverse;
	}
	
	public void setForward(int forward) {
		this.forward = forward;
	}
	public int getForward() {
		return forward;
	}
}
