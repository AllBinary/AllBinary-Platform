package org.allbinary.game.input.analog;

import org.allbinary.graphics.CustomGPoint;

public class AnalogLocationInput {

    public static final AnalogLocationInput NULL_ANALOG_LOCATION_INPUT = new AnalogLocationInput(-1);

	private final int playerId;
	private final CustomGPoint customGPoint;
	private int rz;
        
        private int rightTrigger;
        private int leftTrigger;

	AnalogLocationInput(int playerId) {
		this.playerId = playerId;
		this.customGPoint = new CustomGPoint(0, 0, 0);
	}

	public int getPlayerId() {
		return playerId;
	}

	public CustomGPoint getCustomGPoint() {
		return customGPoint;
	}

	public int getRz() {
		return rz;
	}

	public void setRz(int rz) {
		this.rz = rz;
	}

    /**
     * @return the rightTrigger
     */
    public int getRightTrigger() {
        return rightTrigger;
    }

    /**
     * @param rightTrigger the rightTrigger to set
     */
    public void setRightTrigger(int rightTrigger) {
        this.rightTrigger = rightTrigger;
    }

    /**
     * @return the leftTrigger
     */
    public int getLeftTrigger() {
        return leftTrigger;
    }

    /**
     * @param leftTrigger the leftTrigger to set
     */
    public void setLeftTrigger(int leftTrigger) {
        this.leftTrigger = leftTrigger;
    }
}
