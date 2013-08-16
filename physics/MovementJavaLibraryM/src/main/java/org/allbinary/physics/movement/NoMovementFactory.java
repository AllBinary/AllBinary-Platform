package org.allbinary.physics.movement;

public class NoMovementFactory {

	private static final NoMovementFactory instance = new NoMovementFactory();

	public static NoMovementFactory getInstance() {
		return instance;
	}
	
	private final Movement movement = new Movement();
	
	public final Movement getMovmentInstance()
	{
		return movement;
	}
}
