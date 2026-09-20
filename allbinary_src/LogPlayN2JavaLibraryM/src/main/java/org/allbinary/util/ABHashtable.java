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
    public synchronized int size() {
        return super.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public synchronized Enumeration<V> elements() {
        return super.elements();
    }

    @Override
    public synchronized boolean contains(Object value) {
        return super.contains(value);
    }

    @Override
    public boolean containsValue(Object value) {
        return super.containsValue(value);
    }

    @Override
    public synchronized boolean containsKey(Object key) {
        return super.containsKey(key);
    }

    @Override
    public synchronized V get(Object key) {
        return super.get(key);
    }

    @Override
    public synchronized void clear() {
        super.clear();
    }

    @Override
    public synchronized Object clone() {
        return super.clone();
    }

    @Override
    public synchronized String toString() {
        return super.toString();
    }

    @Override
    public synchronized boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public synchronized int hashCode() {
        return super.hashCode();
    }

}
