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
package org.allbinary.midlet;

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import jsinterop.annotations.JsMethod;


@JsType
public interface MidletCompositeInterface {

	@JsMethod
	void setMidlet(MIDlet midlet);
	@JsMethod
	AllBinaryMidlet getMidlet();
	
	@JsMethod
	Displayable getDisplayable();
	//void setDisplayable(Displayable displayable);
}
