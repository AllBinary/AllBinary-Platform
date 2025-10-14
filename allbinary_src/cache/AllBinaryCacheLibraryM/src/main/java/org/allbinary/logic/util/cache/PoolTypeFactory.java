package org.allbinary.logic.util.cache;

public class PoolTypeFactory {

	private static final PoolTypeFactory instance = new PoolTypeFactory();

	public static PoolTypeFactory getInstance() {
		return instance;
	}
	    
    public PoolType VECTOR_POOL = new PoolType(CacheTypeFactory.getInstance().NO_CACHE);
}
