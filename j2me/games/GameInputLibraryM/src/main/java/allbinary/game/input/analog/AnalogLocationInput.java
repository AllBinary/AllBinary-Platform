package allbinary.game.input.analog;

import allbinary.graphics.CustomGPoint;

public class AnalogLocationInput {

	private final int playerId;
	private final CustomGPoint customGPoint;
	private int rz;

	protected AnalogLocationInput(int playerId) {
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
}
