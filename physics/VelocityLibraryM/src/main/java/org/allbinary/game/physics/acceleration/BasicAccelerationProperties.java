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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

@JsType
public class BasicAccelerationProperties {

	private int forward;
	private int reverse;
	
	@JsConstructor
	public BasicAccelerationProperties(
			int forward, int reverse)
	{
		this.setForward(forward);
		this.setReverse(reverse);
	}
	
	@JsMethod
	public void setReverse(int reverse) {
		this.reverse = reverse;
	}
	@JsMethod
	public int getReverse() {
		return this.reverse;
	}
	
	@JsMethod
	public void setForward(int forward) {
		this.forward = forward;
	}
	@JsMethod
	public int getForward() {
		return this.forward;
	}
}
