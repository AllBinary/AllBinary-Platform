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

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import jsinterop.annotations.JsType;

/**
 *
 * @author User
 */
@JsType
public class ABHashtable<K,V> extends Hashtable<K,V> {

    //GWT does not have the other constructors.
//    public ABHashtable(final int initialCapacity) {
//        super();
//    }

    public ABHashtable() {
        super();
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public Enumeration<V> elements() {
        return super.elements();
    }

    @Override
    public boolean contains(Object value) {
        return super.contains(value);
    }

    @Override
    public boolean containsValue(Object value) {
        return super.containsValue(value);
    }

    @Override
    public boolean containsKey(Object key) {
        return super.containsKey(key);
    }

    @Override
    public V get(Object key) {
        return super.get(key);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Object clone() {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
