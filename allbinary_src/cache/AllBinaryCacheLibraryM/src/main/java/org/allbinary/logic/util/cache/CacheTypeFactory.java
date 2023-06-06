package org.allbinary.logic.util.cache;

import org.allbinary.logic.basic.string.StringUtil;

public class CacheTypeFactory 
{
	private static final CacheTypeFactory instance = new CacheTypeFactory();

	public static CacheTypeFactory getInstance() {
		return instance;
	}
	    
    public CacheType CACHE = new CacheType(null, StringUtil.getInstance().EMPTY_STRING);
    public CacheType SET = new CacheType(null, "Set");
    public CacheType CACHE_VECTOR = new CacheType(null, StringUtil.getInstance().EMPTY_STRING);
}
