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

import java.util.Vector;

import jsinterop.annotations.JsType;

/**
 *
 * @author User
 */
@JsType
public class ABVector<E> extends Vector<E> {

    @Override
    public void ensureCapacity(int minCapacity) {
        super.ensureCapacity(minCapacity);
    }

    @Override
    public int capacity() {
        return super.capacity();
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
    public boolean contains(Object obj) {
        return super.contains(obj);
    }

    @Override
    public int indexOf(Object obj) {
        return super.indexOf(obj);
    }

    @Override
    public int lastIndexOf(Object obj) {
        return super.lastIndexOf(obj);
    }

    @Override
    public E elementAt(int index) {
        return super.elementAt(index);
    }

    @Override
    public E firstElement() {
        return super.firstElement();
    }

    @Override
    public E lastElement() {
        return super.lastElement();
    }

    @Override
    public void setElementAt(E obj, int index) {
        super.setElementAt(obj, index);
    }

    @Override
    public void removeElementAt(int index) {
        super.removeElementAt(index);
    }

    @Override
    public void addElement(E obj) {
        super.addElement(obj);
    }

    @Override
    public void insertElementAt(E obj, int index) {
        super.insertElementAt(obj, index);
    }

    @Override
    public boolean removeElement(Object obj) {
        return super.removeElement(obj);
    }

    @Override
    public void removeAllElements() {
        super.removeAllElements();
    }

    @Override
    public Object clone() {
        return super.clone();
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public E set(int index, E element) {
        return super.set(index, element);
    }

    @Override
    public boolean add(E element) {
        return super.add(element);
    }

    @Override
    public boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);
    }

    @Override
    public E remove(int index) {
        return super.remove(index);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
    }

}
