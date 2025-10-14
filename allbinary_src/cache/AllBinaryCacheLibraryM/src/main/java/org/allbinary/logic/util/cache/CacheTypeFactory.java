package org.allbinary.logic.util.cache;

import org.allbinary.logic.string.StringUtil;

public class CacheTypeFactory 
{
	private static final CacheTypeFactory instance = new CacheTypeFactory();

	public static CacheTypeFactory getInstance() {
		return instance;
	}
	    
    public CacheType NO_CACHE = new CacheType(StringUtil.getInstance().EMPTY_STRING, StringUtil.getInstance().EMPTY_STRING);
    public CacheType CACHE = new CacheType(StringUtil.getInstance().EMPTY_STRING, StringUtil.getInstance().EMPTY_STRING);
    public CacheType SET = new CacheType(StringUtil.getInstance().EMPTY_STRING, "Set");
    public CacheType CACHE_VECTOR = new CacheType(StringUtil.getInstance().EMPTY_STRING, StringUtil.getInstance().EMPTY_STRING);
}
