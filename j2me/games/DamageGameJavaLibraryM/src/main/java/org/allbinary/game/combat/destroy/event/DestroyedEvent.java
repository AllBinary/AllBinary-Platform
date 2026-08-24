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
package org.allbinary.game.combat.destroy.event;

import jsinterop.annotations.JsType;

import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.displayable.canvas.NullGameCanvas;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class DestroyedEvent extends AllBinaryEventObject {
	private AllBinaryGameCanvas allBinaryGameCanvas = NullGameCanvas.getInstance();
	private AllBinaryLayer allBinaryLayerInterface = AllBinaryLayer.NULL_ALLBINARY_LAYER;

	@JsConstructor
	public DestroyedEvent(AllBinaryGameCanvas combatGameCanvas// ,
	// LayerInterface allBinaryLayerInterface
	) {
		super(DestroyedEventHandler.getInstance());
		// this.setLayerInterface(allBinaryLayerInterface);
		this.setAllBinaryGameCanvas(combatGameCanvas);
	}

	@JsMethod
	public AllBinaryGameCanvas getAllBinaryGameCanvas() {
		return this.allBinaryGameCanvas;
	}

	@JsMethod
	public AllBinaryLayer getLayerInterface() {
		return this.allBinaryLayerInterface;
	}

	@JsMethod
	private void setAllBinaryGameCanvas(AllBinaryGameCanvas allBinaryGameCanvas) {
		this.allBinaryGameCanvas = allBinaryGameCanvas;
	}

	/*
	private void setLayerInterface(AllBinaryLayer allBinaryLayerInterface) {
		this.allBinaryLayerInterface = allBinaryLayerInterface;
	}
	*/

	@JsMethod
	public void setLayerInterfaceForCircularStaticPool(AllBinaryLayer allBinaryLayerInterface) {
		this.allBinaryLayerInterface = allBinaryLayerInterface;
	}
	
	@JsMethod
	public String toString()
	{
	      final StringMaker stringBuffer = new StringMaker();
	      
	      stringBuffer.append("DestroyedEvent: \n");
	      stringBuffer.append("AllBinaryLayerInterface: ");
	      stringBuffer.append(this.allBinaryLayerInterface.toString());
	      stringBuffer.append("\nAllBinaryGameCanvas: ");
	      stringBuffer.append(StringUtil.getInstance().toString(this.getAllBinaryGameCanvas()));

	      return stringBuffer.toString();
	}
}
