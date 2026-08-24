package org.allbinary.game.input.analog;

import jsinterop.annotations.JsType;

import org.allbinary.graphics.CustomGPoint;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class AnalogLocationInput {

    @JsProperty
    public static final AnalogLocationInput NULL_ANALOG_LOCATION_INPUT = new AnalogLocationInput(-1);

	private final int playerId;
	private final CustomGPoint customGPoint;
	private int rz;
        
        private int rightTrigger;
        private int leftTrigger;

	@JsConstructor
	AnalogLocationInput(int playerId) {
		this.playerId = playerId;
		this.customGPoint = new CustomGPoint(0, 0, 0);
	}

	@JsMethod
	public int getPlayerId() {
		return this.playerId;
	}

	@JsMethod
	public CustomGPoint getCustomGPoint() {
		return this.customGPoint;
	}

	@JsMethod
	public int getRz() {
		return this.rz;
	}

	@JsMethod
	public void setRz(int rz) {
		this.rz = rz;
	}

    /**
     * @return the rightTrigger
     */
    @JsMethod
    public int getRightTrigger() {
        return this.rightTrigger;
    }

    /**
     * @param rightTrigger the rightTrigger to set
     */
    @JsMethod
    public void setRightTrigger(int rightTrigger) {
        this.rightTrigger = rightTrigger;
    }

    /**
     * @return the leftTrigger
     */
    @JsMethod
    public int getLeftTrigger() {
        return this.leftTrigger;
    }

    /**
     * @param leftTrigger the leftTrigger to set
     */
    @JsMethod
    public void setLeftTrigger(int leftTrigger) {
        this.leftTrigger = leftTrigger;
    }
}
