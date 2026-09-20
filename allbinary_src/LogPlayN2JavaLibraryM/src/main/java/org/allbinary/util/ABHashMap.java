/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
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
package org.allbinary.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jsinterop.annotations.JsType;

/**
 *
 * @author User
 */
@JsType
public class ABHashMap<K,V> extends HashMap<K,V> {

	@Override
	public int size() {
		return super.size();
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public V get(Object key) {
		return super.get(key);
	}

	@Override
	public boolean containsKey(Object key) {
		return super.containsKey(key);
	}

	@Override
	public V put(K key, V value) {
		return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		super.putAll(map);
	}

	@Override
	public V remove(Object key) {
		return super.remove(key);
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public boolean containsValue(Object value) {
		return super.containsValue(value);
	}

	@Override
	public Set<K> keySet() {
		return super.keySet();
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return super.entrySet();
	}

	@Override
	public V getOrDefault(Object key, V defaultValue) {
		return super.getOrDefault(key, defaultValue);
	}

	@Override
	public V putIfAbsent(K key, V value) {
		return super.putIfAbsent(key, value);
	}

	@Override
	public Object clone() {
		return super.clone();
	}

}
